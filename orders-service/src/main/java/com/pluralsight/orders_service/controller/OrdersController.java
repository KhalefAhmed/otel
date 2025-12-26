package com.pluralsight.orders_service.controller;

import com.pluralsight.orders_service.model.Order;
import com.pluralsight.orders_service.service.OrdersService;
import io.micrometer.core.instrument.Metrics;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
@Slf4j
public class OrdersController {

    private OrdersService ordersService;

    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) {
        Order createdOrder = ordersService.createOrder(order);
        return createdOrder;
    }

    @GetMapping("/orders/{orderId}")
    public Order getOrder(@PathVariable String orderId) {
        Order retrievedOrder = ordersService.findOrder(orderId);
        return retrievedOrder;
    }
}
