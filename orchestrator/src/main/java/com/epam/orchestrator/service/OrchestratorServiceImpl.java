package com.epam.orchestrator.service;

import com.epam.library.dto.OrderDto;
import com.epam.library.dto.ProductDto;
import com.epam.library.enums.OrderStatus;
import com.epam.orchestrator.config.StepConfig;
import com.epam.orchestrator.producer.KafkaProducer;
import com.epam.orchestrator.step.OrderStep;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrchestratorServiceImpl implements OrchestratorService {

    @Autowired
    private StepConfig stepConfig;

    private final String ORDER_INSTANCE ="order";

    @Autowired
    private KafkaProducer kafkaProducer;
    @Override
    @CircuitBreaker(name = ORDER_INSTANCE , fallbackMethod = "handleFailure")
    public void processOrder(OrderDto orderDto) throws JsonProcessingException {
        log.info("starting orchestrator steps");
        List<OrderStep> orderSteps = getOrderSteps(orderDto);
        try{
            orderSteps.forEach(OrderStep::process);
            orderDto.setOrderStatus(OrderStatus.CONFIRMED);
            kafkaProducer.sendMessage(orderDto);
        }catch (Exception exception){
            log.info("starting orchestrator rollback steps due to {}",exception.getMessage());
            orderSteps.stream().filter(OrderStep::getStatus).forEach(OrderStep::handleFailure);
            orderDto.setOrderStatus(OrderStatus.CANCELLED);
            kafkaProducer.sendMessage(orderDto);
        }
    }

    public void handleFailure(OrderDto orderDto , Exception exception) throws JsonProcessingException {
        log.info("starting orchestrator fallback due to {}",exception.getMessage());
        orderDto.setOrderStatus(OrderStatus.CANCELLED);
        kafkaProducer.sendMessage(orderDto);
    }

    public List<OrderStep> getOrderSteps(OrderDto orderDto){
        return List.of( getAccountStepForUser(orderDto),getInventoryStep(orderDto) , getAccountStepForSeller(orderDto));
    }

    public OrderStep getInventoryStep(OrderDto orderDto){
        List<ProductDto> products = orderDto.getProducts();
        return stepConfig.getInventoryUpdateStep(products);
    }

    public OrderStep getAccountStepForUser(OrderDto orderDto){
        return stepConfig.getDebitAccountStep(orderDto.getUserId(), orderDto.getAmount());
    }

    public OrderStep getAccountStepForSeller(OrderDto orderDto){
        return  stepConfig.getCreditAccountStep(orderDto.getSellerId(), orderDto.getAmount());
    }
}
