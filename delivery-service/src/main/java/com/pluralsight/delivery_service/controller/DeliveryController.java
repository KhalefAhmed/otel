package com.pluralsight.delivery_service.controller;

import com.pluralsight.delivery_service.model.Delivery;
import com.pluralsight.delivery_service.service.DeliveryService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DeliveryController {

    private DeliveryService deliveryService;

    @Timed(value = "latencyInSec.deliveries", description = "Time taken to create a delivery")
    @Counted(value = "counter.deliveries", description = "Number of deliveries created")
    @PostMapping("/deliveries")
    public Delivery createDelivery(@RequestBody Delivery delivery) {
        return deliveryService.createDelivery(delivery);
    }
}
