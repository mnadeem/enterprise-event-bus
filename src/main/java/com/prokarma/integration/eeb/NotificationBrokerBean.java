package com.prokarma.integration.eeb;

import org.switchyard.component.bean.Service;

@Service(NotificationBroker.class)
public class NotificationBrokerBean implements NotificationBroker {

	private NotificationProcessor processor;

	@Override
	public void notify(Notification notification) {
		this.processor.process(notification);
	}
}
