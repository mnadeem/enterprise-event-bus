package com.prokarma.middleware.eeb.service.support;

import javax.inject.Named;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.switchyard.bus.camel.audit.Auditor;
import org.switchyard.bus.camel.processors.Processors;

/**
 * Auditor surrounding all SY processors.
 */
@Named("simple auditor")
public class SimpleAuditor implements Auditor {

	private static final Logger LOGGER = Logger.getLogger(SimpleAuditor.class);

    @Override
    public void beforeCall(Processors processor, Exchange exchange) {
        exchange.setProperty("time", System.currentTimeMillis());
    }

    @Override
    public void afterCall(Processors processor, Exchange exchange) {
        long time = System.currentTimeMillis() - exchange.getProperty("time", 0, Long.class);
        LOGGER.debug("Step " + processor.name() + " took " + time + "ms");
    }

}
