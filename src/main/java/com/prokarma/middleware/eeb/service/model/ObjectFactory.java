package com.prokarma.middleware.eeb.service.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.prokarma.middleware.eeb.service.Query;

@XmlRegistry
public class ObjectFactory {

    private final static QName _NOTIFICATION_QNAME = new QName("urn:com.prokarma.app.middleware:enterprise-event-bus:1.0", "notification");
    
    private final static QName _QUERY_QNAME = new QName("urn:com.prokarma.app.middleware:enterprise-event-bus:1.0", "query");

    public ObjectFactory() {

    }

    public Query createQuery() {
        return new Query();
    }
    
    public Notification createNotification() {
        return new Notification();
    }

    @XmlElementDecl(namespace = "urn:com.prokarma.app.middleware:enterprise-event-bus:1.0", name = "notification")
    public JAXBElement<Notification> createNotification(Notification value) {
        return new JAXBElement<Notification>(_NOTIFICATION_QNAME, Notification.class, null, value);
    }
    
    @XmlElementDecl(namespace = "urn:com.prokarma.app.middleware:enterprise-event-bus:1.0", name = "query")
    public JAXBElement<Query> createOrder(Query value) {
        return new JAXBElement<Query>(_QUERY_QNAME, Query.class, null, value);
    }
}
