package com.example.kafkademo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaProducer implements CommandLineRunner {

    public static Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducer.class, args).close();
    }

    @Autowired
    private KafkaTemplate<String, String> template;

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> headers = new HashMap<>();
                headers.put(KafkaHeaders.TOPIC, "myTopic");
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(42);

        headers.put(KafkaHeaders.CORRELATION_ID, bb.array());
        GenericMessage<String> message =
            new GenericMessage<>("Hello!", headers);
        this.template.send(message);
    }
}
