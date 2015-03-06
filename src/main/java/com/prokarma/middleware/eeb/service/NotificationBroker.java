package com.prokarma.middleware.eeb.service;

import com.prokarma.middleware.eeb.service.model.Notification;
import com.prokarma.middleware.eeb.service.model.Query;

public interface NotificationBroker {

	void notify(Notification notification);
	
	void forward(Query query);
}
