package com.prokarma.middleware.eeb.store;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prokarma.middleware.eeb.store.support.Util;

public class InMemMessageSubscriptionStore implements MessageSubscriptionStore {
	private static Logger logger = LoggerFactory.getLogger(InMemMessageSubscriptionStore.class);

	private static ConcurrentHashMap<String, MessageSubscription> store = new ConcurrentHashMap<String, MessageSubscription>();

	@Override
	public void store(List<MessageSubscription> messageSubscriptions) {
		if (messageSubscriptions == null || messageSubscriptions.isEmpty()) {
			logger.error("*** Nothing to store ***");
			return ;
		}
		for (MessageSubscription messageSubscription : messageSubscriptions) {
			messageSubscription.setId(Util.generateId());
			store.put(messageSubscription.getId(), messageSubscription);
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
			if (messageSubscription.getTopic().equalsIgnoreCase(topic) && !messageSubscription.isProcessed()) {
				messageSubscriptions.add(messageSubscription);
			}
		}
		return messageSubscriptions;
	}
}
