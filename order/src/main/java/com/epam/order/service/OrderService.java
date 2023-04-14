package com.epam.order.service;

import com.epam.library.dto.OrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface OrderService {
    OrderDto getOrderById(long orderId);

    List<OrderDto> getAllOrders();

    OrderDto createOrder(OrderDto orderDto) throws JsonProcessingException;

    void updateOrderStatus(OrderDto orderDto);

}
