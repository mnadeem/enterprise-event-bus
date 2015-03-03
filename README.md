# Enterprise Event Bus (EEB)
Enterprise Event Bus (Event-Driven SOA, publisher-subscriber or simply EEB) using Fuse Service Works. By default SOAP over http binding is available, However exposing other bindings (REST, JMS, AMQP etc) is just a matter of few clicks in Fuse Service works.
##### Level 1
![eeb1ImageId]
##### Level 2
![eeb2ImageId]
### Here is the Switchyard diagram for publishing feature
![switchyardImageId]

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

# How to Run
##### Configure Camel http in wildfly
Camel http module is not configured by default in jboss-eap, follow the steps to configure one
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
##### Setup endpoint
* By Default in-memory implementation (providing datastore implementation[key-value for message store and rdbms for other configuration] would be a trivial task) is provided and hence it identifies the following topic and endpoints, as things are hard coded. And hence you have to post to those topic and the following end point should be up to receive the notification

```java
static {
		store.put("1", new Subscription("1", "xyz", "http://localhost:7077/notification"));
		store.put("2", new Subscription("2", "xyz", "http://localhost:7077/notification"));
		store.put("1", new Subscription("1", "xyz", "file:///output/"));
	}
```
Alternatively you can change the endpoint in com.prokarma.middleware.eeb.store.InMemSubscriptionStore
##### Start Wildfly Server

##### Deploy the application to server
just run 
```bash
mvn clean install -P deploy
```
Run com.prokarma.middleware.eeb.NotificationListener this would start the notification listener
Run com.prokarma.middleware.eeb.NotificationBrokerTest this would publish the event to EEB


[eeb1ImageId]: http://s15.postimg.org/n4tdptvi3/eeb_level1.png  "EEB Level 1 Diagram"
[eeb2ImageId]: http://s24.postimg.org/tkl5wn11x/eeb_level2.png  "EEB Level 2 Diagram"
[switchyardImageId]: http://s9.postimg.org/o8gmo9b5b/switchyard.png  "Switchyard Component Diagram"

