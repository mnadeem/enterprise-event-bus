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
		List<MessageSubscription> messageSubscriptions = buildMessageSubscriptions(topic, messageId, message);
		doProcessMessage(topic, messageSubscriptions);
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
			return ;
		}
		String topic = query.getTopic();
		List<Subscription> subscriptions = subscriptions(query.getSubscriber());
		if (subscriptions == null || subscriptions.isEmpty()) {
			logger.info("Subscriber {} not recognized ", query.getSubscriber());
			return;
		}
		for (Message message : messages) {
			List<MessageSubscription> messageSubscriptions = newMessageSubscriptions(message.getMessage(), subscriptions, message.getId());
			doProcessMessage(topic, messageSubscriptions);
		}
	}

	private List<Subscription> subscriptions(String subscriberId) {
		List<Subscription> subscriptions = new ArrayList<Subscription>();
		Subscription subscription = this.subscriptionStore.get(subscriberId);
		if (subscription != null) {
			subscriptions.add(subscription);
		}
		return subscriptions;
	}

	private void doProcessMessage(String topic, List<MessageSubscription> messageSubscriptions) {

		if (!messageSubscriptions.isEmpty()) {			
			this.messsageSubscriptionStore.store(messageSubscriptions);
			processSubscritions(topic);
		} else {
			logger.info("No subscriptions for topic {}", topic);
		}
	}
	
	private List<MessageSubscription> buildMessageSubscriptions(String topic, String messageId, String message) {
		List<Subscription> subscriptions = this.subscriptionStore.getSubscriptions(topic);
		return newMessageSubscriptions(message, subscriptions, messageId);
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
