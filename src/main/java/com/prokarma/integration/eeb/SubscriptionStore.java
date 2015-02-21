package com.prokarma.integration.eeb;

import java.util.List;

public interface SubscriptionStore {

	List<Subscription> getSubscriptions(String topic);

}
