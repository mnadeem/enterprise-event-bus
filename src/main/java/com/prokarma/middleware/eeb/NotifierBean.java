package com.prokarma.middleware.eeb;

import javax.inject.Inject;

import org.switchyard.component.bean.Service;

@Service(Notifier.class)
public class NotifierBean implements Notifier {

	@Inject
	private NotificationPublisher notificationPublisher;
	
	@Override
	public void notify(String topic) {
		System.out.println("NotifierBean " + topic);
		notificationPublisher.publish(topic);
	}

}
