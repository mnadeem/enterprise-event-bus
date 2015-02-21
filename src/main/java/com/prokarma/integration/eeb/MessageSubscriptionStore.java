package com.prokarma.integration.eeb;

import java.util.List;

public interface MessageSubscriptionStore {

	void store(List<MessageSubscription> newMessageSubscriptions);

}
