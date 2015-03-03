package com.prokarma.middleware.eeb.store;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Named;
@Named
public class InMemSubscriptionStore implements SubscriptionStore {

	private static ConcurrentHashMap<String, Subscription> store = new ConcurrentHashMap<String, Subscription>();

	static {
		store.put("1", new Subscription("1", "xyz", "http://localhost:7077/notification"));
		store.put("2", new Subscription("2", "xyz", "http://localhost:7077/notification"));
		store.put("1", new Subscription("1", "xyz", "file:///output/"));
	}

	@Override
	public List<Subscription> getSubscriptions(String topic) {
		List<Subscription> subscriptions = new ArrayList<Subscription>();
		for (Subscription subscription : store.values()) {
			if (subscription.getTopic().equalsIgnoreCase(topic)) {
				subscriptions.add(subscription);
			}
		}
		return subscriptions;
	}
}
