package com.epam.orchestrator.producer;

import com.epam.library.dto.OrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${spring.kafka.producer.topic}")
    private String orderTopic;

    public void sendMessage(OrderDto orderDto) throws JsonProcessingException {
        log.info("sending order status");
        kafkaTemplate.send(orderTopic, objectMapper.writeValueAsString(orderDto));
    }
}
