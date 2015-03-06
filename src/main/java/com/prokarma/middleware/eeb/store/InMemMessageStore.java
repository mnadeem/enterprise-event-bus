package com.prokarma.middleware.eeb.store;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.inject.Default;

import org.joda.time.DateTime;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.prokarma.middleware.eeb.store.support.Util;

@Default
public class InMemMessageStore implements MessageStore {

	private ConcurrentHashMap<String, Message> store = new ConcurrentHashMap<String, Message>();

	@Override
	public String store(Message message) {
		message.setId(Util.generateId());
		message.setCreationDateTime(new DateTime());
		this.store.put(message.getId(), message);
		return message.getId();
	}

	@Override
	public List<Message> find(final String topic, final DateTime from, final DateTime to) {
		return FluentIterable
				.from(this.store.values())
				.filter(new Predicate<Message>() {

					@Override
					public boolean apply(Message message) {
						return message.getTopic().equalsIgnoreCase(topic) && inRange(message.getCreationDateTime(), from, to);
					}

					private boolean inRange(DateTime creationDateTime, DateTime from, DateTime to) {
						return (creationDateTime.isEqual(from) || from.isAfter(from)) && (creationDateTime.isEqual(to) || from.isBefore(to));
					}
				})
				.toList();
	}

	@Override
	public Message get(String messageId) {
		return this.store.get(messageId);
	}
}
