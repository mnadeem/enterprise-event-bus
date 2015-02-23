package com.prokarma.middleware.eeb;

public interface NotificationBroker {

	void notify(Notification notifcation) throws NotificationException;
}
