package com.pluralsight.orders_service.service;

import com.pluralsight.orders_service.client.DeliveryClient;
import com.pluralsight.orders_service.exception.OrderNotFoundException;
import com.pluralsight.orders_service.model.Order;
import com.pluralsight.orders_service.repository.OrdersRepository;
import io.micrometer.core.instrument.Metrics;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OrdersService {

    private static final String CREATED = "CREATED";
    private static final String IN_DELIVERY = "IN_DELIVERY";

    private OrdersRepository ordersRepository;
    private DeliveryClient deliveryClient;

    public Order createOrder(Order order) {
        log.info("Creating order: {}", order);
        order.setOrderStatus(CREATED);
        ordersRepository.save(order);
        log.info("Order created with id: {}", order.getOrderId());
        deliveryClient.createDeliveryForOrder(order);
        order.setOrderStatus(IN_DELIVERY);
        log.info("Order status updated to IN_DELIVERY for order id: {}", order.getOrderId());
        return order;
    }

    public Order findOrder(String orderId) {
        log.info("Finding order with id: {}", orderId);
        return ordersRepository.findById(orderId)
                .orElseThrow(() -> {
                    log.error("Order with id {} not found", orderId);
                    Metrics.counter("counter.orders.retrieved.count.failure").increment();
                    return new OrderNotFoundException("Order with id " + orderId + " not found");});
    }

}
