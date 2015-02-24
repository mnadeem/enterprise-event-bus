package com.prokarma.middleware.eeb;

import org.switchyard.component.bean.Service;

@Service(Notifier.class)
public class NotifierBean implements Notifier {
	
	
	@Override
	public void notify(String topic) {
		System.out.println("NotifierBean " + topic);
	}

}
