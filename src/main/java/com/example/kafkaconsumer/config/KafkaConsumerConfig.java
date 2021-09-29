package com.example.kafkaconsumer.config;

import com.example.kafkaconsumer.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@PropertySource("classpath:environment.yaml")
@Slf4j
@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    private final String bootstrapAddress;
    private final String groupId;
    private final int numThreads;

    public static final String AUTO_COMMIT_INTERVAL_MS = "100";
    public static final String SESSION_TIMEOUT_MS = "15000";
    public static final long RETRY_BACKOFF_MS = 2_000;
    public static final int REQUEST_TIMEOUT_MS = 305_000;

    public KafkaConsumerConfig(@Value("${bootstrap-servers}") String bootstrapAddress,
                               @Value("${group-id}") String groupId,
                               @Value("${num-threads}") int numThreads) {
        this.bootstrapAddress = bootstrapAddress;
        this.groupId = groupId;
        this.numThreads = numThreads;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, User> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(objectConsumerFactory());
        factory.setConcurrency(numThreads);

        return factory;
    }

    @Bean
    public ConsumerFactory<String, User> objectConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, AUTO_COMMIT_INTERVAL_MS);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, SESSION_TIMEOUT_MS);
        props.put(ConsumerConfig.RETRY_BACKOFF_MS_CONFIG, RETRY_BACKOFF_MS);
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, REQUEST_TIMEOUT_MS);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        JsonDeserializer<User> jsonDeserializer = new JsonDeserializer<>(User.class);
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.addTrustedPackages("*");
        jsonDeserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }
}
