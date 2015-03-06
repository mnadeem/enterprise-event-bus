package com.prokarma.middleware.eeb.business;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.Reference;

import com.prokarma.middleware.eeb.service.Notifier;
import com.prokarma.middleware.eeb.service.model.Notification;
import com.prokarma.middleware.eeb.service.model.Query;
import com.prokarma.middleware.eeb.store.Message;
import com.prokarma.middleware.eeb.store.MessageStore;
import com.prokarma.middleware.eeb.store.MessageSubscription;
import com.prokarma.middleware.eeb.store.MessageSubscriptionStore;
import com.prokarma.middleware.eeb.store.Subscription;
import com.prokarma.middleware.eeb.store.SubscriptionStore;

@Named("notificationProcessor")
@Default
public class DefaultNotificationProcessor implements NotificationProcessor {

	private static Logger logger = LoggerFactory.getLogger(DefaultNotificationProcessor.class);

	@Inject
	private MessageStore messageStore;
	@Inject
	private NotificationValidator validator;
	@Inject
	@Reference
	private Notifier notifier;
	@Inject
	private SubscriptionStore subscriptionStore;
	@Inject
	private MessageSubscriptionStore messsageSubscriptionStore;

	@Override
	public void process(Notification notification) {
		String messageId = this.messageStore.store(newMessage(notification));
		this.validator.validate(notification);
		String topic = notification.getTopic();
		String message = notification.getMessage();
		doProcessMessage(topic, messageId, message);
	}

	private Message newMessage(Notification notification) {
		return new Message(notification.getPublisher(),
				notification.getTopic(), notification.getMessage());
	}

	@Override
	public void handleQuery(Query query) {
		List<Message> messages = this.messageStore.find(query.getTopic(), query.getFrom(), query.getTo());
		if (messages == null || messages.isEmpty()) {
			logger.info("No Messages to forward for topic {} for given date range ", query.getTopic());
		}
		String topic = query.getTopic();
		for (Message message : messages) {
			doProcessMessage(topic, message.getId(), message.getMessage());
		}
	}

	private void doProcessMessage(String topic, String messageId, String message) {
		List<Subscription> subscriptions = this.subscriptionStore.getSubscriptions(topic);
		if (!subscriptions.isEmpty()) {
			this.messsageSubscriptionStore.store(newMessageSubscriptions(message, subscriptions, messageId));
			processSubscritions(topic);
		} else {
			logger.info("No subscriptions for topic {}", topic);
		}
	}

	private void processSubscritions(String topic) {
		for (MessageSubscription messageSubscription : messageSubscriptions(topic)) {
			try {
				notifySubscriber(messageSubscription);
			} catch (Exception e) {
				logger.error("Error Notifying :" + messageSubscription.getRecipients(), e);
			}
		}
	}

	private List<MessageSubscription> messageSubscriptions(String topic) {
		return this.messsageSubscriptionStore.find(topic);
	}

	private void notifySubscriber(MessageSubscription messageSubscription) {
		this.notifier.notify(messageSubscription);
	}

	private List<MessageSubscription> newMessageSubscriptions(String message, List<Subscription> subscriptions, String messageId) {
		List<MessageSubscription> messageSubscriptions = new ArrayList<MessageSubscription>();
		for (Subscription subscription : subscriptions) {
			messageSubscriptions.add(new MessageSubscription(messageId,
					subscription.getTopic(), message,
					subscription.getEndpoint()));
		}
		return messageSubscriptions;
	}
}
