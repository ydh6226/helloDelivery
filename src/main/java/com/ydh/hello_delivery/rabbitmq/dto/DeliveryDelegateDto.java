package com.ydh.hello_delivery.rabbitmq.dto;

import com.ydh.hello_delivery.entity.Address;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryDelegateDto {

    private Long deliveryId;
    private String memberName;
    private String itemName;
    private Address address;
}
