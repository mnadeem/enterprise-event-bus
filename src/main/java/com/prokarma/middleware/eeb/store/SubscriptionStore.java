package com.prokarma.middleware.eeb.store;

import java.util.List;

public interface SubscriptionStore {

	List<Subscription> getSubscriptions(String topic);
	
	Subscription get(String id);
}
