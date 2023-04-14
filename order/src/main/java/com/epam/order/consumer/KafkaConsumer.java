package com.epam.order.consumer;

import com.epam.library.dto.OrderDto;
import com.epam.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "order-status")
    public void consumeKafkaMessage(String message) throws JsonProcessingException {
        log.info("consuming order");
        orderService.updateOrderStatus(objectMapper.readValue(message , OrderDto.class));
    }


}
