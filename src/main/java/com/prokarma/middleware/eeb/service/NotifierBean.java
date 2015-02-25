package com.prokarma.middleware.eeb.service;

import javax.inject.Inject;

import org.switchyard.component.bean.Service;

import com.prokarma.middleware.eeb.business.NotificationPublisher;

@Service(Notifier.class)
public class NotifierBean implements Notifier {

	@Inject
	private NotificationPublisher notificationPublisher;
	
	@Override
	public void notify(String topic) {
		System.out.println("NotifierBean**" + topic);
		notificationPublisher.publish(topic);
	}

}
