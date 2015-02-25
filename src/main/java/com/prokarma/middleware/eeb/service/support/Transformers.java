package com.prokarma.middleware.eeb.service.support;

import org.switchyard.annotations.Transformer;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.prokarma.middleware.eeb.service.model.Notification;

public final class Transformers {

	@Transformer(from = "{urn:com.prokarma.app.middleware:enterprise-event-bus:1.0}notify")
	public Notification transformNotifyToNotification(Element from) {
		System.out.println("Trnsforming : " + from);
		 return new Notification()
         .setMessage(getElementValue(from, "message"))
         .setTopic(getElementValue(from, "topic"))
         .setPublisher(getElementValue(from, "publisher"));
	}
	
	private String getElementValue(Element parent, String elementName) {
        String value = null;
        NodeList nodes = parent.getElementsByTagName(elementName);
        if (nodes.getLength() > 0) {
            value = nodes.item(0).getChildNodes().item(0).getNodeValue();
        }
        return value;
    }

}
