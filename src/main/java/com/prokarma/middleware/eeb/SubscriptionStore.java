package com.prokarma.middleware.eeb;

import java.util.List;

public interface SubscriptionStore {

	List<Subscription> getSubscriptions(String topic);

}
