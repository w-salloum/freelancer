package com.home.freelancer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GenericEventListener {

    @KafkaListener(topics = "${spring.kafka.topics.freelancer-created}", groupId = "${spring.kafka.consumer.group-id}\"")
    public void consumeFreelancerCreatedEvent(Object event) {
        log.info("Consumed FreelancerCreatedEvent from Kafka: {}", event);
    }

}
