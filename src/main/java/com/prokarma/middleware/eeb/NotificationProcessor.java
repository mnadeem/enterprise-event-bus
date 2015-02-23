package com.prokarma.middleware.eeb;

public interface NotificationProcessor {

	void process(Notification notification) throws NotificationException;

}
