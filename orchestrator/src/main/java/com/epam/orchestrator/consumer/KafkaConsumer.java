package com.epam.orchestrator.consumer;

import com.epam.library.dto.OrderDto;
import com.epam.orchestrator.service.OrchestratorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    private OrchestratorService orchestratorService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "order")
    public void consumeKafkaMessage(String message) throws JsonProcessingException {
        log.info("consuming order message");
        orchestratorService.processOrder(objectMapper.readValue(message , OrderDto.class));
    }
}
