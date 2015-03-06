package com.prokarma.middleware.eeb.store;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Named;

import org.joda.time.DateTime;

import com.prokarma.middleware.eeb.store.support.Util;

@Named
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message get(String messageId) {
		// TODO Auto-generated method stub
		return null;
	}
}
