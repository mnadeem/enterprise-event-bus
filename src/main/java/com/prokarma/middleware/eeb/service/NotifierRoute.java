package com.prokarma.middleware.eeb.service;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.prokarma.middleware.eeb.store.MessageSubscription;

public class NotifierRoute extends RouteBuilder {

	public void configure() throws Exception {

		from("switchyard://Notifier")
		.process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				MessageSubscription messageSubscription = exchange.getIn().getBody(MessageSubscription.class);
				exchange.getOut().setBody(messageSubscription.getMessage());
				exchange.getOut().setHeader("recipients", messageSubscription.getRecipients());
				exchange.getOut().setHeader("subscriptionId", messageSubscription.getId());
				System.out.println("recipients " + messageSubscription.getRecipients());
				System.out.println("recipients " + messageSubscription.getMessage());
			}
		}).recipientList(header("recipients"))
		.to("bean:subscriptionPostProcessor");
	}
}
