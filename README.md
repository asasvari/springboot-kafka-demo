Spring-Kafka demo app

Code is based on https://docs.spring.io/spring-kafka/reference/htmlsingle/#_even_quicker_with_spring_boot
    
- ``KafkaProducer`` is a ``SpringBootApplication``. 
- ``KafkaProducer``'s ``run()`` method sends messages to a topic.
- ``KafkaProducer`` starts a separate thread to consume messages from the topic (see ``@KafkaListener``).
- ``KafkaProducer`` uses a CountDownLatch object for synchronization. 
- ``KafkaProducerConfig`` is used to configure the Kafka clients (see ``@Configuration``). 
- A ``KafkaTemplate`` object is used for communication with the broker. 
- Dependency injection is done with the ``@Autowired`` annotation in ``KafkaProducer``.  

Prerequisites
- JDK 8, Scala 2.1x, maven, gradle 

Build and start Kafka
```
git clone https://github.com/apache/kafka
cd kafka
gradle # if required, download it from https://gradle.org/install/#manually
./gradlew releaseTarGz
nohup bin/zookeeper-server-start.sh ./config/zookeeper.properties 2>&1 >zk1.out&
nohup bin/kafka-server-start.sh config/server.properties 2>&1 > server-src-1.out & 
```

Build and start the demo app:
```
git clone https://github.com/asasvari/springboot-kafka-demo
mvn install 
java -jar target/kafkademo-0.0.1-SNAPSHOT.jar
```
