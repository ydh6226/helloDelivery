package com.ydh.hello_delivery.entity;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
@Getter
public class DeliveryTime {
    private final LocalDateTime registerTime;
    private LocalDateTime shippedTime;
    private LocalDateTime compTime;

    protected DeliveryTime() {
        registerTime = LocalDateTime.now();
    }

    protected void shippedTime() {
        shippedTime = LocalDateTime.now();
    }

    protected void compTime() {
        compTime = LocalDateTime.now();
    }

    public String strGetRegisterTime() {
        return registerTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m"));
    }

    public String strGetShippedTime() {
        return shippedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m"));
    }

    public String strGetCompTime() {
        return compTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m"));
    }
}
