package com.example.kafkaconsumer.listener;

import com.example.kafkaconsumer.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class KafkaConsumerTest {
    @SpyBean
    private KafkaConsumer kafkaConsumer;

    @Test
    void greetingListener() {
        kafkaConsumer.greetingListener(new User());
    }
}