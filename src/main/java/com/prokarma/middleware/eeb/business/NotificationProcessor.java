package com.prokarma.middleware.eeb.business;

import com.prokarma.middleware.eeb.service.model.Notification;
import com.prokarma.middleware.eeb.service.model.Query;

public interface NotificationProcessor {

	void process(Notification notification);

	void handleQuery(Query query);

}
