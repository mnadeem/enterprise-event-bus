package com.prokarma.middleware.eeb.business;

import com.prokarma.middleware.eeb.common.NotificationException;
import com.prokarma.middleware.eeb.service.model.Notification;

public interface NotificationProcessor {

	void process(Notification notification) throws NotificationException;

}
