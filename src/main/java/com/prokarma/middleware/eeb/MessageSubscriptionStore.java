package com.prokarma.middleware.eeb;

import java.util.List;

public interface MessageSubscriptionStore {

	void store(List<MessageSubscription> newMessageSubscriptions);

	void update(MessageSubscription messageSubscription);

}
