package com.prokarma.middleware.eeb.store;

public class MessageSubscription {

	private String id;
	private String messageId;
	private String recipients;
	private boolean processed;
	private String topic;
	//Non persistent
	private String message;

	public MessageSubscription() {

	}

	public MessageSubscription(String messageId, String topic, String message, String recipients) {
		this.messageId = messageId;
		this.topic = topic;
		this.message = message;
		this.recipients = recipients;
	}

	public String getMessage() {
		return this.message;
	}

	public Object getRecipients() {
		return this.recipients;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "MessageSubscription [id=" + id + ", message=" + message
				+ ", messageId=" + messageId + ", recipients=" + recipients
				+ ", processed=" + processed + "]";
	}
}
