package com.prokarma.middleware.eeb.service.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="notification")
public class Notification {

	private String topic;
	private String message;
	private String publisher;

	public String getTopic() {
		return topic;
	}
	public Notification setTopic(String topic) {
		this.topic = topic;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public Notification setMessage(String message) {
		this.message = message;
		return this;
	}
	public String getPublisher() {
		return publisher;
	}
	public Notification setPublisher(String publisher) {
		this.publisher = publisher;
		return this;
	}
	@Override
	public String toString() {
		return "Notification [topic=" + topic + ", message=" + message
				+ ", publisher=" + publisher + "]";
	}
}
