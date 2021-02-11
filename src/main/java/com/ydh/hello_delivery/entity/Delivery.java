package com.ydh.hello_delivery.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@NoArgsConstructor
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

    //== 비즈니스 로직 ==//
    public void startDelivery() {
        deliveryStatus = DeliveryStatus.SHIPPED;
        deliveryTime.shippedTime();
    }

    public void startDeliveryRollback() {
        deliveryStatus = DeliveryStatus.READY;
    }

    public void endDelivery() {
        deliveryStatus = DeliveryStatus.COMP;
        deliveryTime.compTime();
    }

    public void endDeliveryRollback() {
        deliveryStatus = DeliveryStatus.READY;
    }
}
