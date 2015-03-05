package com.prokarma.middleware.eeb.store;

import java.util.Date;
import java.util.List;

public interface MessageStore {

	String store(Message newMessage);

	List<String> find(String topic, Date from, Date to);

	Message get(String messageId);
}
