package com.prokarma.middleware.eeb.store;

import java.util.List;

public interface MessageSubscriptionStore {

	void store(List<MessageSubscription> newMessageSubscriptions);

	void update(MessageSubscription messageSubscription);

	MessageSubscription load(String id);

	List<MessageSubscription> find(String topic);

}
