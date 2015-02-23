package com.prokarma.middleware.eeb;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.switchyard.component.bean.Reference;

@Named
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
		System.out.println("DefaultNotificationProcessor" + notification);
		String id = this.messageStore.store(newMessage(notification));
		this.validator.validate(notification);
		List<Subscription> subscriptions =  this.subscriptionStore.getSubscriptions(notification.getTopic());
		this.messsageSubscriptionStore.store(newMessageSubscriptions(notification, subscriptions, id));
		this.notifier.notify(notification.getTopic());
	}

	private List<MessageSubscription> newMessageSubscriptions(Notification notification, List<Subscription> subscriptions, String id) {
		return Collections.emptyList();
	}

	private Message newMessage(Notification notification) {
		return new Message();
	}
}
