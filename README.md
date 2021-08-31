# Kafka Demo

## Objectives

Implements a simple kafka producer/consumer messages to help understand key concepts. This project uses spring boot features.

## References 
* https://www.baeldung.com/spring-kafka
* https://emmanuelneri.com.br/2019/06/04/kafka-no-spring-boot
* https://kafka.apache.org/quickstart

## How to use (locally)

Install Kafka according the start reference: https://kafka.apache.org/quickstart

Open the terminal (linux or mac) or git bash terminal (windows) and run the follow commands:

Create the topic to receive messages:
```shell
$KAFKA_PATH/bin/kafka-topic.sh --create \
--topic your-topic-name \
--bootstrap-server localhost:9092
```
&nbsp;

Show all incoming messages in the new topic
```shell
$KAFKA_PATH/bin/kafka-console-consumer.sh --topic your-topic-name \
--from-beginning \ 
--bootstrap-server localhost:9092
```

&nbsp;

Clean and update maven dependencies:
```shell
mvn clean install && mvn package
```

&nbsp;

Start project:
```shell
mvn spring-boot:run
```

&nbsp;

Send message over curl:
```shell
curl --location --request POST http://localhost:8080/app/sendMessage \
--header 'Content-Type: application/json' \
--data-raw '{"id": "01","description": "description for message 01","version": "1.0"}'
```

The service responses with the Kafka Send Result body:
```shell
{"message":"{\"producerRecord\":\"ProducerRecord(topic=business-topic, partition=null, headers=RecordHeaders(headers = [], isReadOnly = true), key=null, value={\\\"description\\\":\\\"description for message 01\\\",\\\"id\\\":\\\"01\\\",\\\"version\\\":\\\"1.0\\\"}, timestamp=null)\",\"recordMetadata\":\"business-topic-0@138\"}","httpStatus":"OK"}
```

> ### REPOSITORY

```https
https://github.com/eviccari/kafka-demo
```

&nbsp;

> #### ENVOLVED TECHNOLOGIES

- Java 
- Spring-boot
- kafka

&nbsp;

> #### MASTER DEPENDENCIES

&nbsp;

> ### ROADMAP