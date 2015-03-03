package com.prokarma.middleware.eeb;

import org.switchyard.component.test.mixins.http.HTTPMixIn;


public class NotificationBrokerTest {

	private static final String XML = "src/test/resources/xml/soap-request.xml";

	public static void main(String[] args) {
		postNotificationToEEB();
	}

	private static void postNotificationToEEB() {
		HTTPMixIn soapMixIn = new HTTPMixIn();
		soapMixIn.initialize();

		try {
			String port = System.getProperty("org.switchyard.component.soap.client.port", "8080");
			String url = "http://localhost:" + port + "/eeb/NotificationBroker";
			soapMixIn.postFile(url, XML);

		} finally {
			soapMixIn.uninitialize();
		}
	}
}
