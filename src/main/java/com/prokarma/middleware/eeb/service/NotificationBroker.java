package com.prokarma.middleware.eeb.service;

import com.prokarma.middleware.eeb.common.NotificationException;
import com.prokarma.middleware.eeb.service.model.Notification;

public interface NotificationBroker {

	void notify(Notification notifcation) throws NotificationException;
}
