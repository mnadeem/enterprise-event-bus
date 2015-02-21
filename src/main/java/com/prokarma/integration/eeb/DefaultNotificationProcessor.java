package com.prokarma.integration.eeb;

import java.util.List;

public class DefaultNotificationProcessor implements NotificationProcessor {

	private MessageStore messageStore;
	private NotificationValidator validator;
	private Notifier notifier;
	private SubscriptionStore subscriptionStore;
	private MessageSubscriptionStore messsageSubscriptionStore;

	@Override
	public void process(Notification notification) {
		String id = this.messageStore.store(newMessage(notification));
		this.validator.validate(notification);
		List<Subscription> subscriptions =  this.subscriptionStore.getSubscriptions(notification.getTopic());
		this.messsageSubscriptionStore.store(newMessageSubscriptions(notification, subscriptions, id));
		this.notifier.notifySubscribers(notification.getTopic());
	}

	private List<MessageSubscription> newMessageSubscriptions(Notification notification, List<Subscription> subscriptions, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	private Message newMessage(Notification notification) {
		// TODO Auto-generated method stub
		return null;
	}
}
