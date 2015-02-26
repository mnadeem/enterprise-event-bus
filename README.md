# enterprise-event-bus
Enterprise Event Bus using Fuse Service Works

# Pre requisites
Camel http module is not configured by default in jboss-eap, follow the steps to configure on
* copy module.xml to modules\system\layers\soa\org\apache\camel\http\main
* copy camel-http-2.14.0.jar to modules\system\layers\soa\org\apache\camel\http\main
* look for the following in your jboss server configuration and the camel http extension 
```xml
<subsystem xmlns="urn:jboss:domain:switchyard:1.0">
<extensions>
<extension identifier="org.apache.camel.http"/>
</extensions>
</subsystem>
```
