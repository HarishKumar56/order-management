package com.epam.order.service;

import com.epam.library.dto.OrderDto;
import com.epam.library.enums.OrderStatus;
import com.epam.library.exception.NotFoundException;
import com.epam.order.entity.OrderDo;
import com.epam.order.producer.KafkaProducer;
import com.epam.order.repo.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaProducer kafkaProducer;
    @Override
    public OrderDto getOrderById(long orderId) {
        OrderDo order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException("order not found"));
        return objectMapper.convertValue(order , OrderDto.class);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(order -> objectMapper.convertValue(order , OrderDto.class)).collect(Collectors.toList());
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) throws JsonProcessingException {
        OrderDo order = objectMapper.convertValue(orderDto , OrderDo.class);
        order.setOrderStatus(OrderStatus.PENDING);
        order = orderRepository.save(order);
        orderDto.setOrderId(order.getOrderId());
        kafkaProducer.sendMessage(orderDto);
        return objectMapper.convertValue(order , OrderDto.class);
    }

    @Override
    public void updateOrderStatus(OrderDto orderDto) {
        OrderDo order = orderRepository.findById(orderDto.getOrderId()).orElse(new OrderDo());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setOrderId(orderDto.getOrderId());
        orderRepository.save(order);
    }
}
