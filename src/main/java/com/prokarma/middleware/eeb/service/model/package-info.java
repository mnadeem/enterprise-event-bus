@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(type=DateTime.class, value=DateTimeAdapter.class)
})
@javax.xml.bind.annotation.XmlSchema(namespace = "urn:com.prokarma.app.middleware:enterprise-event-bus:1.0")
package com.prokarma.middleware.eeb.service.model;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

import org.joda.time.DateTime;

