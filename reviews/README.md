#DEMO Integration Project

##About the project
"DEMO Library Reviews Application" implements OpenId Authentication and AppDirect Susbcription Management Endpoints. It's built on 
appengine-skeleton archetype and running on Google Application Engine platform. 
DEMO doesn't persist any data to the datastore. It's just writing log messages to react to events.
 
All application pages are protected by openId. If you need to disable openId protection just comment out OpenIdFilter in web.xml.
reviews\src\main\resources\oauth.properties is OAuth configuration to store Consumer Key and Consumer Secret. Before running or deploying
you need to provide both.

Only handleADEvent method of ADSubscriptionManagementEndpoint class is covered by unit test (JUnit/Mockito). In real project unit test coverage should be at lease 80%.


##Building the project:
==in command prompt or Shell
mvn clean install


##Running the project locally:
==in command prompt or Shell
cd ad-integration-ear
mvn appengine:devserver

##Delploy on Google App Engine:
==in command prompt or Shell
mvn appengine:update


