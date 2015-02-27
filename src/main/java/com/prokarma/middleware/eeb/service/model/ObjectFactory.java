package com.prokarma.middleware.eeb.service.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _NOTIFICATION_QNAME = new QName("urn:com.prokarma.app.middleware:enterprise-event-bus:1.0", "notification");

    public ObjectFactory() {

    }

    public Notification createNotification() {
        return new Notification();
    }

    @XmlElementDecl(namespace = "urn:com.prokarma.app.middleware:enterprise-event-bus:1.0", name = "notification")
    public JAXBElement<Notification> createNotification(Notification value) {
        return new JAXBElement<Notification>(_NOTIFICATION_QNAME, Notification.class, null, value);
    }
}
