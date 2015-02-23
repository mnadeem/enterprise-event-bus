package com.prokarma.middleware.eeb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.SwitchYardTestKit;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = { CDIMixIn.class })
public class NotificationBrokerTest {

	private SwitchYardTestKit testKit;
	private CDIMixIn cdiMixIn;
	@ServiceOperation("NotificationBroker")
	private Invoker service;

	@Test
	public void testNotify() throws Exception {
		// TODO Auto-generated method stub
		// initialize your test message
		Notification message = new Notification();
		service.operation("notify").sendInOnly(message);

		// validate the results
		//Assert.assertTrue("Implement me", false);
	}

}