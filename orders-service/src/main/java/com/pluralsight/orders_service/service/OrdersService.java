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
        order.setOrderStatus(CREATED);
        ordersRepository.save(order);
        deliveryClient.createDeliveryForOrder(order);
        order.setOrderStatus(IN_DELIVERY);
        return order;
    }

    public Order findOrder(String orderId) {
        return ordersRepository.findById(orderId)
                .orElseThrow(() -> {
                    return new OrderNotFoundException("Order with id " + orderId + " not found");});
    }

}
