package com.prokarma.middleware.eeb.store;

import org.joda.time.DateTime;

public class Message {

	private String id;
	private String message;
	private String topic;
	private String createdBy;
	private DateTime creationDateTime;

	public Message(String publisher, String topic, String message) {
		this.createdBy = publisher;
		this.topic = topic;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public DateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(DateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	@Override
	public String toString() {
		return String.format("Message [id=%s, message=%s, topic=%s]", id,
				message, topic);
	}

}
