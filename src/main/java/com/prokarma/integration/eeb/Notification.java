package com.prokarma.integration.eeb;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Notification {

	public String topic;
	public String producer;
	public Object message;
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}	
}
