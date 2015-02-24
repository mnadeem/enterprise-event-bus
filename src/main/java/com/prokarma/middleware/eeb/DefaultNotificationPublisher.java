package com.prokarma.middleware.eeb;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DefaultNotificationPublisher implements NotificationPublisher {

	@Inject
	private MessageSubscriptionStore messsageSubscriptionStore;

	@Override
	public void publish(String topic) {
		for (MessageSubscription messageSubscription : messageSubscriptions(topic)) {
			try {
				notifySubscriber(messageSubscription);
				messageSubscription.setProcessed(true);
				messsageSubscriptionStore.update(messageSubscription);
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
		}		
	}

	private void notifySubscriber(MessageSubscription messageSubscription) {
		// TODO Auto-generated method stub
	}

	private List<MessageSubscription> messageSubscriptions(String topic) {
		return Collections.emptyList();
	}

}
