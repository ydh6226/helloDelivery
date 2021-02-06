package com.ydh.hello_delivery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Delivery {
    @Id @GeneratedValue(generator = "delivery_id")
    @Column("delivery_id")
    private Long id;
    String name;
}
