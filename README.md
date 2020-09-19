# Divolte Kafka Consumer Application
Simple Spring Boot application that can consume click-stream events generated by Divolte Collection and published to Kafka brokers

## Divolte
Divolte Collector is a scalable and performant server for collecting clickstream data in HDFS and on Kafka topics.

## Kafka
Apache Kafka is an open-source distributed event streaming platform used by thousands of companies for high-performance data pipelines, streaming analytics, data integration, and mission-critical applications.

## Clickstream data
All clickstream data generated from client is posted from Divolte.js to Divolte-Collector. The Divolte Collecter has a avro schema (avsc file) mapping based on which it structures the data. However as Kafka dosen't understand schema, the avro schema mapped data is serialized and put into Kafka.

This spring boot app has a kafka listener configured that deserializes the data unto an auto generated Avro Schema.

## Build

to generate the avro schema from the avsc file located at src/main/resources.
`
maven generate-source
`
Make sure that your custom avsc file is also placed in the same location. The schema java file will generated in target\generated-sources . (Auto generated java for default divolte clickstream record is committed to src\main\java\com\divolte\consumer\dto\DefaultEventRecord.java )


## Running dependencies
In order to try out this application you will need to bring up zookeeper, kafka and divolte-collector instances. You can use the docker-compose.yaml file present inside *dependecy* folder and run the command
`
docker-compose up --build
`

## Testing
Once all the docker instances are up you can run the spring boot application which will start listening to the *divolte* kafka topic. Access the below url which is a sample UI application which can generate clickstream data.

http://localhost:8290/

## Issues
There is a known compatibility issue https://github.com/spring-projects/spring-boot/issues/14622 which leads to issue below

`
org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method could not be invoked with the incoming message
Endpoint handler details:
Method [public void com.wish.wos.service.KafkaEventListener.receive(com.wish.wos.dto.DefaultEventRecord,java.util.Map<java.lang.String, java.lang.Object>)]
Bean [com.wish.wos.service.KafkaEventListener@5fbbdf41]; nested exception is org.springframework.messaging.converter.MessageConversionException: Cannot handle message; nested exception is org.springframework.messaging.converter.MessageConversionException: Cannot convert from [com.wish.wos.dto.DefaultEventRecord] to [com.wish.wos.dto.DefaultEventRecord] for GenericMessage [payload={"detectedDuplicate": false, "detectedCorruption": false, "firstInSession": false, "timestamp": 1600482800753, "clientTimestamp": 1600482800746, "remoteHost": "172.26.0.1", "referer": null, "location": "http://localhost:8290/", "viewportPixelWidth": 1242, "viewportPixelHeight": 568, "screenPixelWidth": 1366, "screenPixelHeight": 728, "partyId": "0:key3poa5:8kGHX~tBdswzSAeueNiS4Y7D0HeiGaBJ", "sessionId": "0:kf81bj6r:w61Jj3sG9UUqBGQQJmcRdgDX~mTxse4l", "pageViewId": "0:wUwcOEWCgvtFt4opTP9EQmnQAdp8XFtI", "eventType": "bannerClick", "userAgentString": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36", "userAgentName": "Chrome", "userAgentFamily": "Chrome", "userAgentVendor": "Google Inc.", "userAgentType": "Browser", "userAgentVersion": "85.0.4183.102", "userAgentDeviceCategory": "Personal computer", "userAgentOsFamily": "Windows", "userAgentOsVersion": "10.0", "userAgentOsVendor": "Microsoft Corporation."}, headers={kafka_offset=42, kafka_consumer=org.apache.kafka.clients.consumer.KafkaConsumer@7c34cc81, kafka_timestampType=CREATE_TIME, kafka_receivedPartitionId=0, kafka_receivedMessageKey=0:key3poa5:8kGHX~tBdswzSAeueNiS4Y7D0HeiGaBJ, kafka_receivedTopic=divolte, kafka_receivedTimestamp=1600482800753, kafka_groupId=group-id}], failedMessage=GenericMessage [payload={"detectedDuplicate": false, "detectedCorruption": false, "firstInSession": false, "timestamp": 1600482800753, "clientTimestamp": 1600482800746, "remoteHost": "172.26.0.1", "referer": null, "location": "http://localhost:8290/", "viewportPixelWidth": 1242, "viewportPixelHeight": 568, "screenPixelWidth": 1366, "screenPixelHeight": 728, "partyId": "0:key3poa5:8kGHX~tBdswzSAeueNiS4Y7D0HeiGaBJ", "sessionId": "0:kf81bj6r:w61Jj3sG9UUqBGQQJmcRdgDX~mTxse4l", "pageViewId": "0:wUwcOEWCgvtFt4opTP9EQmnQAdp8XFtI", "eventType": "bannerClick", "userAgentString": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36", "userAgentName": "Chrome", "userAgentFamily": "Chrome", "userAgentVendor": "Google Inc.", "userAgentType": "Browser", "userAgentVersion": "85.0.4183.102", "userAgentDeviceCategory": "Personal computer", "userAgentOsFamily": "Windows", "userAgentOsVersion": "10.0", "userAgentOsVendor": "Microsoft Corporation."}, headers={kafka_offset=42, kafka_consumer=org.apache.kafka.clients.consumer.KafkaConsumer@7c34cc81, kafka_timestampType=CREATE_TIME, kafka_receivedPartitionId=0, kafka_receivedMessageKey=0:key3poa5:8kGHX~tBdswzSAeueNiS4Y7D0HeiGaBJ, kafka_receivedTopic=divolte, kafka_receivedTimestamp=1600482800753, kafka_groupId=group-id}]; nested exception is org.springframework.messaging.converter.MessageConversionException: Cannot handle message; nested exception is org.springframework.messaging.converter.MessageConversionException: Cannot convert from [com.wish.wos.dto.DefaultEventRecord] to [com.wish.wos.dto.DefaultEventRecord] for GenericMessage [payload={"detectedDuplicate": false, "detectedCorruption": false, "firstInSession": false, "timestamp": 1600482800753, "clientTimestamp": 1600482800746, "remoteHost": "172.26.0.1", "referer": null, "location": "http://localhost:8290/", "viewportPixelWidth": 1242, "viewportPixelHeight": 568, "screenPixelWidth": 1366, "screenPixelHeight": 728, "partyId": "0:key3poa5:8kGHX~tBdswzSAeueNiS4Y7D0HeiGaBJ", "sessionId": "0:kf81bj6r:w61Jj3sG9UUqBGQQJmcRdgDX~mTxse4l", "pageViewId": "0:wUwcOEWCgvtFt4opTP9EQmnQAdp8XFtI", "eventType": "bannerClick", "userAgentString": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36", "userAgentName": "Chrome", "userAgentFamily": "Chrome", "userAgentVendor": "Google Inc.", "userAgentType": "Browser", "userAgentVersion": "85.0.4183.102", "userAgentDeviceCategory": "Personal computer", "userAgentOsFamily": "Windows", "userAgentOsVersion": "10.0", "userAgentOsVendor": "Microsoft Corporation."}, headers={kafka_offset=42, kafka_consumer=org.apache.kafka.clients.consumer.KafkaConsumer@7c34cc81, kafka_timestampType=CREATE_TIME, kafka_receivedPartitionId=0, kafka_receivedMessageKey=0:key3poa5:8kGHX~tBdswzSAeueNiS4Y7D0HeiGaBJ, kafka_receivedTopic=divolte, kafka_receivedTimestamp=1600482800753, kafka_groupId=group-id}], failedMessage=GenericMessage [payload={"detectedDuplicate": false, "detectedCorruption": false, "firstInSession": false, "timestamp": 1600482800753, "clientTimestamp": 1600482800746, "remoteHost": "172.26.0.1", "referer": null, "location": "http://localhost:8290/", "viewportPixelWidth": 1242, "viewportPixelHeight": 568, "screenPixelWidth": 1366, "screenPixelHeight": 728, "partyId": "0:key3poa5:8kGHX~tBdswzSAeueNiS4Y7D0HeiGaBJ", "sessionId": "0:kf81bj6r:w61Jj3sG9UUqBGQQJmcRdgDX~mTxse4l", "pageViewId": "0:wUwcOEWCgvtFt4opTP9EQmnQAdp8XFtI", "eventType": "bannerClick", "userAgentString": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36", "userAgentName": "Chrome", "userAgentFamily": "Chrome", "userAgentVendor": "Google Inc.", "userAgentType": "Browser", "userAgentVersion": "85.0.4183.102", "userAgentDeviceCategory": "Personal computer", "userAgentOsFamily": "Windows", "userAgentOsVersion": "10.0", "userAgentOsVendor": "Microsoft Corporation."}, headers={kafka_offset=42, kafka_consumer=org.apache.kafka.clients.consumer.KafkaConsumer@7c34cc81, kafka_timestampType=CREATE_TIME, kafka_receivedPartitionId=0, kafka_receivedMessageKey=0:key3poa5:8kGHX~tBdswzSAeueNiS4Y7D0HeiGaBJ, kafka_receivedTopic=divolte, kafka_receivedTimestamp=1600482800753, kafka_groupId=group-id}]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.decorateException(KafkaMessageListenerContainer.java:1925) ~[spring-kafka-2.5.5.RELEASE.jar:2.5.5.RELEASE]
`

