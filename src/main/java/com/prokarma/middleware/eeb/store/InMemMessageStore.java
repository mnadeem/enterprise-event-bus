package com.prokarma.middleware.eeb.store;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.inject.Default;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

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
						return message.getTopic().equalsIgnoreCase(topic) && inRange(from.toDate(), to.toDate(), message.getCreationDateTime().toDate());
					}

					public boolean isDateInBetween(final Date start, final Date end, final Date date)
				    {
				        LocalDate localStartDate = new LocalDate(start);
				        LocalDate localEndDate = new LocalDate(end);
				        LocalDate localDate = new LocalDate(date);

				        if ((localStartDate.isBefore(localDate) || localStartDate.isEqual(localDate))
				            && (localDate.isBefore(localEndDate) || localDate.isEqual(localEndDate)))
				        {
				            return true;
				        }
				        return false;
				    }

				    public boolean isSameDay(final Date date1, final Date date2)
				    {
				        LocalDate dt1 = new LocalDate(date1);
				        LocalDate dt2 = new LocalDate(date2);

				        return dt1.isEqual(dt2);
				    }
				    public boolean inRange(final Date start, final Date end, final Date date)
				    {
				        boolean result = false;
				        if (isSameDay(start, date) || isSameDay(end, date))
				        {
				            result = true;
				        }
				        else if (isDateInBetween(start, end, date))
				        {
				            result = true;
				        }
				        return result;
				    }
				})
				.toList();
	}

	@Override
	public Message get(String messageId) {
		return this.store.get(messageId);
	}
}
