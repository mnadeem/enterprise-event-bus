package com.prokarma.middleware.eeb.store;

public class MessageSubscription {
	
	private String message;
	private String recipients;
	

	public MessageSubscription() {

	}

	public MessageSubscription(String message, String recipients) {
		this.message = message;
		this.recipients = recipients;
	}
	
	public void setProcessed(boolean b) {
		// TODO Auto-generated method stub		
	}

	public String getMessage() {
		return this.message;
	}

	public Object getRecipients() {
		return this.recipients;
	}

	public String getId() {
		return "1";
	}

}
