package com.prokarma.middleware.eeb.service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.joda.time.DateTime;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "topic",
    "publisher",
    "from",
    "to"
})
@XmlRootElement(name="query")
public class Query {

	private String topic;
	private DateTime from;
	private DateTime to;
	private String publisher;

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
	public String getPublisher() {
		return publisher;
	}
	public Query setPublisher(String publisher) {
		this.publisher = publisher;
		return this;
	}

	@Override
	public String toString() {
		return String.format("Query [topic=%s, from=%s, to=%s, publisher=%s]",
				topic, from, to, publisher);
	}	
}
