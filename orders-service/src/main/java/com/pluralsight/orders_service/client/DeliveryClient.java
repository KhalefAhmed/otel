package com.pluralsight.orders_service.client;

import com.pluralsight.orders_service.exception.DeliveryCreationException;
import com.pluralsight.orders_service.model.Delivery;
import com.pluralsight.orders_service.model.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@AllArgsConstructor
public class DeliveryClient {

    private static final String BASE_URL = "http://localhost:6001";
    private static final String DELIVERIES_URI = "/deliveries";

    private RestTemplate restTemplate;

    public Delivery createDeliveryForOrder(Order order) {
        Delivery generatedDelivery = fromOrder(order);
        ResponseEntity<Delivery> delivery;
        try {
            delivery = restTemplate.postForEntity(BASE_URL + DELIVERIES_URI, generatedDelivery, Delivery.class);
        } catch (Exception e) {
            throw new DeliveryCreationException("Failed to create delivery for order " + order);
        }
        return delivery.getBody();
    }

    private Delivery fromOrder(Order order) {
        return Delivery.builder()
                .source(order.getSource())
                .destination(order.getDestination())
                .build();
    }

}
