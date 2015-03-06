package com.prokarma.middleware.eeb.business;

import javax.enterprise.inject.Default;
import javax.inject.Named;

import com.prokarma.middleware.eeb.service.model.Notification;

@Named
@Default
public class DefaultNotificationValidator implements NotificationValidator {

	@Override
	public void validate(Notification notification) {
		// TODO Auto-generated method stub
	}

}
