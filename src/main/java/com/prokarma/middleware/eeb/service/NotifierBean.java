package com.prokarma.middleware.eeb.service;

import org.switchyard.component.bean.Service;

import com.prokarma.middleware.eeb.store.MessageSubscription;

@Service(Notifier.class)
public class NotifierBean implements Notifier {
	
	@Override
	public void notify(MessageSubscription messageSubscription) {
		System.out.println("NotifierBean**" + messageSubscription);
	}

}
