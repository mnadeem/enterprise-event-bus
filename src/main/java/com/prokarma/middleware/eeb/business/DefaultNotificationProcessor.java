package com.prokarma.middleware.eeb.business;

import java.util.ArrayList;
import java.util.Collections;
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

		String id = this.messageStore.store(newMessage(notification));
		this.validator.validate(notification);
		List<Subscription> subscriptions =  this.subscriptionStore.getSubscriptions(notification.getTopic());
		this.messsageSubscriptionStore.store(newMessageSubscriptions(notification, subscriptions, id));

		processSubscritions(notification);
	}

	private void processSubscritions(Notification notification) {
		for (MessageSubscription messageSubscription : messageSubscriptions(notification.getTopic())) {
			try {
				notifySubscriber(messageSubscription);
			} catch (Exception e) {

			}
		}
	}

	private List<MessageSubscription> newMessageSubscriptions(Notification notification, List<Subscription> subscriptions, String id) {
		return Collections.emptyList();
	}

	private Message newMessage(Notification notification) {
		return new Message();
	}

	private void notifySubscriber(MessageSubscription messageSubscription) {
		this.notifier.notify(messageSubscription);
	}

	private List<MessageSubscription> messageSubscriptions(String topic) {
		List<MessageSubscription> subscriptions = new ArrayList<MessageSubscription>();
		subscriptions.add(new MessageSubscription("1", "Hello Event1", "http://localhost:8080/http-example/"));
		subscriptions.add(new MessageSubscription("2","Hello Event2", "http://localhost:8080/http-example/"));
		subscriptions.add(new MessageSubscription("3","Hello Event3", "file:///output/"));
		return subscriptions;
	}
}
