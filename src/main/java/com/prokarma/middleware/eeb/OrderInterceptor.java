package com.prokarma.middleware.eeb;

import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import org.switchyard.Exchange;
import org.switchyard.ExchangeInterceptor;
import org.switchyard.ExchangeState;
import org.switchyard.HandlerException;

@Named
public class OrderInterceptor implements ExchangeInterceptor {

    @Override
    public void before(String target, Exchange exchange) throws HandlerException {
    	if (exchange.getProvider().getName().getLocalPart().equals("NotificationBrokerService")) {
           System.out.println("Exchange started for NotificationBrokerService");
        }
    }

    @Override
    public void after(String target, Exchange exchange) throws HandlerException {
        // We only want to intercept successful replies from OrderService
        if (exchange.getProvider().getName().getLocalPart().equals("NotificationBrokerService")) {
           if (ExchangeState.OK.equals(exchange.getState())) {
			System.out.println("Good Exchange for NotificationBrokerService");
           } else {
        	   System.out.println("Faulty Exchange for NotificationBrokerService");
           }
        }
    }

    @Override
    public List<String> getTargets() {
        return Arrays.asList(PROVIDER);
    }

}
