package com.prokarma.middleware.eeb.business;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.switchyard.component.bean.Reference;

import com.prokarma.middleware.eeb.service.Notifier;
import com.prokarma.middleware.eeb.service.model.Notification;
import com.prokarma.middleware.eeb.store.Message;
import com.prokarma.middleware.eeb.store.MessageStore;
import com.prokarma.middleware.eeb.store.MessageSubscription;
import com.prokarma.middleware.eeb.store.MessageSubscriptionStore;
import com.prokarma.middleware.eeb.store.Subscription;
import com.prokarma.middleware.eeb.store.SubscriptionStore;

@Named("notificationProcessor")
public class DefaultNotificationProcessor implements NotificationProcessor {

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
		List<Subscription> subscriptions =  this.subscriptionStore.getSubscriptions(notification.getTopic());
		this.messsageSubscriptionStore.store(newMessageSubscriptions(notification, subscriptions, messageId));

		processSubscritions(notification);
	}

	private void processSubscritions(Notification notification) {
		for (MessageSubscription messageSubscription : messageSubscriptions(notification.getTopic())) {
			try {
				notifySubscriber(messageSubscription);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private List<MessageSubscription> newMessageSubscriptions(Notification notification, List<Subscription> subscriptions, String messageId) {
		List<MessageSubscription> messageSubscriptions = new ArrayList<MessageSubscription>();
		for (Subscription subscription : subscriptions) {
			messageSubscriptions.add(new MessageSubscription(messageId, notification.getTopic(), notification.getMessage(), subscription.getEndpoint()));
		}
		return messageSubscriptions;
	}

	private Message newMessage(Notification notification) {
		return new Message();
	}

	private void notifySubscriber(MessageSubscription messageSubscription) {
		this.notifier.notify(messageSubscription);
	}

	private List<MessageSubscription> messageSubscriptions(String topic) {
		return this.messsageSubscriptionStore.find(topic);
	}
}
