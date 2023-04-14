package com.epam.order.controller;

import com.epam.library.dto.OrderDto;
import com.epam.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/order-service/")
@RestController
@Slf4j
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public OrderDto createOrder(@RequestBody OrderDto orderDto) throws JsonProcessingException {
        log.info("creating order");
        return orderService.createOrder(orderDto);
    }

    @GetMapping("/orders")
    public List<OrderDto> getAllOrders(){
        log.info("getting all order");
        return orderService.getAllOrders();

    }

    @GetMapping("/orders/{orderId}")
    public OrderDto getOrderById(@PathVariable long orderId){
        log.info("getting order by id");
        return orderService.getOrderById(orderId);
    }
}
