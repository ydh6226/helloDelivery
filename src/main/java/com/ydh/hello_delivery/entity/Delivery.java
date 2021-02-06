package com.ydh.hello_delivery.entity;

import lombok.Getter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "orders")
@Getter
public class Delivery {
    @Id
    @Column(name = "delivery_id")
    private Long id;

    private String memberName;

    private String itemName;

    @Embedded
    private Address address;

    @Enumerated(STRING)
    private DeliveryStatus deliveryStatus;

    @Embedded
    private DeliveryTime deliveryTime;

    protected Delivery(Long id, String memberName, String itemName, Address address) {
        this.id = id;
        this.memberName = memberName;
        this.itemName = itemName;
        this.address = address;
    }

    public static Delivery createDelivery(Long id, String memberName, String itemName, Address address) {
        Delivery delivery = new Delivery(id, memberName, itemName, address);
        delivery.deliveryStatus = DeliveryStatus.READY;
        delivery.deliveryTime = new DeliveryTime();

        return delivery;
    }
}
