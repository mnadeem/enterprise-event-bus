package com.prokarma.middleware.eeb.service;

import javax.inject.Inject;

import org.switchyard.component.bean.Service;

import com.prokarma.middleware.eeb.business.NotificationProcessor;
import com.prokarma.middleware.eeb.common.NotificationException;
import com.prokarma.middleware.eeb.service.model.Notification;

@Service(NotificationBroker.class)
public class NotificationBrokerBean implements NotificationBroker {
	@Inject
	private NotificationProcessor processor;

	@Override
	public void notify(Notification notification) throws NotificationException {		
		processor.process(notification);
	}

}
