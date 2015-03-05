package com.prokarma.middleware.eeb.business;

import com.prokarma.middleware.eeb.service.Query;
import com.prokarma.middleware.eeb.service.model.Notification;

public interface NotificationProcessor {

	void process(Notification notification);

	void handleQuery(Query query);

}
