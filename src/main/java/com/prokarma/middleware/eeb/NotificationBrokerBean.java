package com.prokarma.middleware.eeb;

import javax.inject.Inject;

import org.switchyard.component.bean.Service;

@Service(NotificationBroker.class)
public class NotificationBrokerBean implements NotificationBroker {
	@Inject
	private NotificationProcessor processor;
	
	@Override
	public void notify(Notification notification) throws NotificationException {		
		processor.process(notification);
	}

}
