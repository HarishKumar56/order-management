package com.epam.orchestrator.service;

import com.epam.library.dto.OrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrchestratorService {

    void processOrder(OrderDto orderDto) throws JsonProcessingException;
}
