package com.prokarma.middleware.eeb.service;

import com.prokarma.middleware.eeb.store.MessageSubscription;

public interface Notifier {
	void notify(MessageSubscription messageSubscription);
}
