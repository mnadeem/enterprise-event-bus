package com.prokarma.middleware.eeb.service.support;

import org.joda.time.DateTime;
import org.switchyard.annotations.Transformer;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.prokarma.middleware.eeb.service.model.Notification;
import com.prokarma.middleware.eeb.service.model.Query;

public final class Transformers {

	@Transformer(from = "{urn:com.prokarma.app.middleware:enterprise-event-bus:1.0}notify")
	public Notification transformNotifyToNotification(Element from) {
		 return new Notification()
         .setMessage(getElementValue(from, "message"))
         .setTopic(getElementValue(from, "topic"))
         .setPublisher(getElementValue(from, "publisher"));
	}

	@Transformer(from = "{urn:com.prokarma.app.middleware:enterprise-event-bus:1.0}forward")
	public Query transformForwardToQuery(Element from) {
		// TODO Auto-generated method stub
		return new Query()
			.setTopic(getElementValue(from, "topic"))
			.setPublisher(getElementValue(from, "publisher"))
			.setFrom(new DateTime(getElementValue(from, "from")))
			.setTo(new DateTime(getElementValue(from, "to")));
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
