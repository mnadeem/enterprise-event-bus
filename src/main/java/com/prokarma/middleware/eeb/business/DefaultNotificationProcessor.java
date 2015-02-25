package com.prokarma.middleware.eeb.business;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.switchyard.component.bean.Reference;

import com.prokarma.middleware.eeb.service.Notifier;
import com.prokarma.middleware.eeb.service.model.Notification;
import com.prokarma.middleware.eeb.store.Message;
import com.prokarma.middleware.eeb.store.MessageStore;
import com.prokarma.middleware.eeb.store.MessageSubscription;
import com.prokarma.middleware.eeb.store.MessageSubscriptionStore;
import com.prokarma.middleware.eeb.store.Subscription;
import com.prokarma.middleware.eeb.store.SubscriptionStore;

@Named
public class DefaultNotificationProcessor implements NotificationProcessor {
	
	private static final Logger LOGGER = Logger.getLogger(DefaultNotificationProcessor.class);
	
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
		LOGGER.debug("DefaultNotificationProcessor" + notification);
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
