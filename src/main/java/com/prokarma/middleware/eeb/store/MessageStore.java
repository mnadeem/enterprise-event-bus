package com.prokarma.middleware.eeb.store;

import java.util.List;

import org.joda.time.DateTime;

public interface MessageStore {

	String store(Message newMessage);

	List<String> find(String topic, DateTime from, DateTime to);

	Message get(String messageId);
}
