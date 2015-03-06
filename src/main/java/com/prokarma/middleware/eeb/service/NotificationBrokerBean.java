package com.prokarma.middleware.eeb.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.Service;

import com.prokarma.middleware.eeb.business.NotificationProcessor;
import com.prokarma.middleware.eeb.service.model.Notification;
import com.prokarma.middleware.eeb.service.model.Query;

@Service(NotificationBroker.class)
public class NotificationBrokerBean implements NotificationBroker {

	private static Logger LOGGER = LoggerFactory.getLogger(NotificationBrokerBean.class);

	@Inject
	private NotificationProcessor processor;

	@Override
	public void notify(Notification notification) {
		LOGGER.info("Receive notificaton {} ", notification);
		processor.process(notification);
	}

	@Override
	public void forward(Query query) {
		LOGGER.info("Receive query {} ", query);
		processor.handleQuery(query);
	}
}
