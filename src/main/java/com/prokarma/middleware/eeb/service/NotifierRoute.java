package com.prokarma.middleware.eeb.service;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.prokarma.middleware.eeb.store.MessageSubscription;
import com.prokarma.middleware.eeb.store.MessageSubscriptionStore;

public class NotifierRoute extends RouteBuilder {
	
	@Inject private MessageSubscriptionStore store;

	/**
	 * The Camel route is configured via this method.  The from endpoint is required to be a SwitchYard service.
	 */
	public void configure() throws Exception {

		from("switchyard://Notifier")
		.log("Received message for 'NotifierRoute' : ${body}")
		.process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				MessageSubscription messageSubscription = exchange.getIn().getBody(MessageSubscription.class);
				exchange.getOut().setBody(messageSubscription.getMessage());
				exchange.getOut().setHeader("recipients", messageSubscription.getRecipients());
				exchange.getOut().setHeader("subscriptionId", messageSubscription.getId());
			}
		}).recipientList(header("recipients"))
		.process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				if (exchange.getIn() != null) {
					String id = (String) exchange.getIn().getHeader("subscriptionId");
					MessageSubscription messageSubscription = store.get(id);
					messageSubscription.setProcessed(true);
					if (messageSubscription != null) {
						store.update(messageSubscription);
					}
				}
			}
		});
	}
}
