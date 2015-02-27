package com.prokarma.middleware.eeb.store;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.prokarma.middleware.eeb.store.support.Util;

public class InMemMessageSubscriptionStore implements MessageSubscriptionStore {
	
	private static ConcurrentHashMap<String, MessageSubscription> store = new ConcurrentHashMap<String, MessageSubscription>();

	@Override
	public void store(List<MessageSubscription> newMessageSubscriptions) {
		for (MessageSubscription messageSubscription : newMessageSubscriptions) {
			store.put(Util.generateId(), messageSubscription);
		}
	}

	@Override
	public void update(MessageSubscription messageSubscription) {
		store.put(messageSubscription.getId(), messageSubscription);		
	}

	@Override
	public MessageSubscription load(String id) {
		return store.get(id);
	}

	@Override
	public List<MessageSubscription> find(String topic) {
		List<MessageSubscription> messageSubscriptions = new ArrayList<MessageSubscription>();
		for (MessageSubscription messageSubscription : store.values()) {
			if (messageSubscription.getTopic().equalsIgnoreCase(topic)) {
				messageSubscriptions.add(messageSubscription);
			}
		}
		return messageSubscriptions;
	}
}
