package com.prokarma.middleware.eeb.service;

import com.prokarma.middleware.eeb.service.model.Notification;

public interface NotificationBroker {

	void notify(Notification notification);
	
	void forward(Query query);
}
