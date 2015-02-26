package com.prokarma.middleware.eeb.service.support;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.camel.Header;

import com.prokarma.middleware.eeb.store.MessageSubscription;
import com.prokarma.middleware.eeb.store.MessageSubscriptionStore;

@Named("subscriptionPostProcessor")
public class SubscriptionPostProcessor {

	@Inject
	private MessageSubscriptionStore messsageSubscriptionStore;

	public void updateSubscription(@Header("subscriptionId") String subscriptionId) {
		MessageSubscription messageSubscription = messsageSubscriptionStore.load(subscriptionId);
		messageSubscription.setProcessed(true);
		this.messsageSubscriptionStore.update(messageSubscription);
	}
}
