# Enterprise Event Bus (EEB)
Enterprise Event Bus (Event-Driven SOA, publisher-subscriber or simple EEB) using Fuse Service Works, By default SOAP over http binding is available, However exposing other bindings (REST, JMS, AMQP etc) is just a matter of few clicks in Fuse Service works

# EEB Features
* Store and Forward
* Throttling
* Translation
* Filtering
* Push and Pull


# Why Jboss Fuse Service Works
* Exposing Any end point (SOAP, REST, JMS AMQP etc) is just a matter of few clicks
* Monitoring and Governance support
* Built in support to integrate with BPMN, Drools and Camel.
* Can be deployed to OSGI as well as server
* JEE support

# Pre requisites
Camel http module is not configured by default in jboss-eap, follow the steps to configure on
* copy module.xml to modules\system\layers\soa\org\apache\camel\http\main
* copy camel-http-2.14.0.jar to modules\system\layers\soa\org\apache\camel\http\main
* look for the following in your jboss server configuration and add the camel http extension 

```xml
	<subsystem xmlns="urn:jboss:domain:switchyard:1.0">
		<extensions>
		<extension identifier="org.apache.camel.http"/>
		</extensions>
	</subsystem>
```
* By Default in-momory implemention (Howerver providing datastore implementation would be a trivial task) is provided and hence it identifies the following topic and endpoints, as things are hard coded. And hence you have to post to those topics and the following end point should be up to receive the notification

```java
static {
		store.put("1", new Subscription("1", "xyz", "http://localhost:8080/http-example/"));
		store.put("2", new Subscription("2", "xyz", "http://localhost:8080/http-example/"));
		store.put("1", new Subscription("1", "xyz", "file:///output/"));
	}
```