package com.pluralsight.delivery_service.controller;

import com.pluralsight.delivery_service.model.Delivery;
import com.pluralsight.delivery_service.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DeliveryController {

    private DeliveryService deliveryService;

    @PostMapping("/deliveries")
    public Delivery createDelivery(@RequestBody Delivery delivery) {
        return deliveryService.createDelivery(delivery);
    }
}
