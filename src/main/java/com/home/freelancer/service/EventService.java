package com.home.freelancer.service;

import com.home.freelancer.dto.FreelancerResponse;
import com.home.freelancer.event.FreelancerCreatedEvent;
import com.home.freelancer.event.FreelancerDeletedEvent;
import com.home.freelancer.event.FreelancerUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topics.freelancer-created}")
    private String freelancerCreatedTopic;

    @Value("${spring.kafka.topics.freelancer-updated}")
    private String freelancerUpdatedTopic;

    @Value("${spring.kafka.topics.freelancer-deleted}")
    private String freelancerDeletedTopic;

    public void publishEvent(String topic, Object event) {
        try {
            log.info("Publishing event to Kafka: Topic: {}, Event: {}", topic, event);
            kafkaTemplate.send(topic, event);
        } catch (Exception e) {
            log.error("Failed to publish event to Kafka: {}", e.getMessage(), e);
        }
    }

    public void publishFreelancerCreatedEvent(FreelancerResponse freelancerResponse) {
        publishEvent(freelancerCreatedTopic, new FreelancerCreatedEvent(freelancerResponse));
    }

    public void publishFreelancerUpdatedEvent(FreelancerResponse freelancerResponse) {
        publishEvent(freelancerUpdatedTopic, new FreelancerUpdatedEvent(freelancerResponse));
    }

    public void publishFreelancerDeletedEvent(FreelancerResponse freelancerResponse) {
        publishEvent(freelancerDeletedTopic, new FreelancerDeletedEvent(freelancerResponse));
    }

}
