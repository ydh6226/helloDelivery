package com.ydh.hello_delivery.controller;

import com.ydh.hello_delivery.entity.DeliveryStatus;
import lombok.Data;

@Data
public class DeliverySearch {

    private String memberName;
    private DeliveryStatus deliveryStatus;
}
