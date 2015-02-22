package com.prokarma.integration.eeb;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
@Asynchronous
public class NotifierBean implements Notifier {

	@Override
	public Future<String> notifySubscribers(String topic) {
		// TODO Auto-generated method stub
		return new AsyncResult<String>("");
	}

}
