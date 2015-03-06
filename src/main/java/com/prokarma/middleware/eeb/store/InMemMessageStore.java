package com.prokarma.middleware.eeb.store;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.inject.Default;

import org.joda.time.DateTime;

import com.prokarma.middleware.eeb.store.support.Util;

@Default
public class InMemMessageStore implements MessageStore {

	private ConcurrentHashMap<String, Message> store = new ConcurrentHashMap<String, Message>();

	@Override
	public String store(Message message) {
		message.setId(Util.generateId());
		message.setCreationDateTime(new Date());
		this.store.put(message.getId(), message);
		return message.getId();
	}

	@Override
	public List<String> find(String topic, DateTime from, DateTime to) {
		return Collections.emptyList();
	}

	@Override
	public Message get(String messageId) {
		// TODO Auto-generated method stub
		return null;
	}
}
