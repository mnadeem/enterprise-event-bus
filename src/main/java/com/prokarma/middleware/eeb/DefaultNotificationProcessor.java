package com.prokarma.middleware.eeb;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DefaultNotificationProcessor implements NotificationProcessor {
	@Inject
	private MessageStore messageStore;
	@Inject
	private NotificationValidator validator;

	@Inject
	private SubscriptionStore subscriptionStore;
	@Inject
	private MessageSubscriptionStore messsageSubscriptionStore;

	@Override
	public void process(Notification notification) {
		String id = this.messageStore.store(newMessage(notification));
		this.validator.validate(notification);
		List<Subscription> subscriptions =  this.subscriptionStore.getSubscriptions(notification.getTopic());
		this.messsageSubscriptionStore.store(newMessageSubscriptions(notification, subscriptions, id));
		//this.notifier.notifySubscribers(notification.getTopic());
		System.out.println("DefaultNotificationProcessor" + notification);
	}

	private List<MessageSubscription> newMessageSubscriptions(Notification notification, List<Subscription> subscriptions, String id) {
		return Collections.emptyList();
	}

	private Message newMessage(Notification notification) {
		return new Message();
	}
}
