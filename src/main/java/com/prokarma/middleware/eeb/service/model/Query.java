package com.prokarma.middleware.eeb.service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.joda.time.DateTime;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "topic",
    "subscriber",
    "from",
    "to"
})
@XmlRootElement(name="query")
public class Query {

	private String topic;
	private DateTime from;
	private DateTime to;
	private String subscriber;

	public String getTopic() {
		return topic;
	}
	public Query setTopic(String topic) {
		this.topic = topic;
		return this;
	}
	public DateTime getFrom() {
		return from;
	}
	public Query setFrom(DateTime from) {
		this.from = from;
		return this;
	}
	public DateTime getTo() {
		return to;
	}
	public Query setTo(DateTime to) {
		this.to = to;
		return this;
	}

	public String getSubscriber() {
		return subscriber;
	}

	public Query setSubscriber(String subscriber) {
		this.subscriber = subscriber;
		return this;
	}

	@Override
	public String toString() {
		return String.format("Query [topic=%s, from=%s, to=%s, subscriber=%s]",
				topic, from, to, subscriber);
	}
}
