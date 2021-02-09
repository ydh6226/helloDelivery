package com.ydh.hello_delivery.rabbitmq.dto;

import com.ydh.hello_delivery.entity.Address;
import lombok.Data;

@Data
public class DeliveryDto {
    private Long deliveryId;
    private String memberName;
    private String itemName;
    private Address address;

}
