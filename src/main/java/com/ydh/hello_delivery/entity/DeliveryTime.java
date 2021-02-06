package com.ydh.hello_delivery.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
public class DeliveryTime {
    private final LocalDateTime receptionTime;
    private LocalDateTime shippedTime;
    private LocalDateTime arrivalTime;

    protected DeliveryTime() {
        receptionTime = LocalDateTime.now();
    }

    protected void shippedTime() {
        shippedTime = LocalDateTime.now();
    }

    protected void arrivalTime() {
        arrivalTime = LocalDateTime.now();
    }
}
