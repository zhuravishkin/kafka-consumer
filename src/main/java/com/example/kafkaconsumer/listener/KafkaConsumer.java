package com.example.kafkaconsumer.listener;

import com.example.kafkaconsumer.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {
    private static final String TOPIC = "src-topic";

//    @KafkaListener(topics = TOPIC, groupId = "groupId-app", topicPartitions = @TopicPartition(topic = TOPIC,
//            partitionOffsets = {@PartitionOffset(partition = "0", initialOffset = "0")}))
//    public void listen(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//        log.info("Received message in group: " + message + " from partition: " + partition);
//    }
//
//    @KafkaListener(topics = TOPIC, groupId = "groupId-app", containerFactory = "filterKafkaListenerContainerFactory")
//    public void listen(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//        log.info("Received message in group: " + message + " from partition: " + partition);
//    }

    @KafkaListener(topics = TOPIC, groupId = "groupId-json")
    public void greetingListener(User user) {
        log.info(user.toString());
    }
}
