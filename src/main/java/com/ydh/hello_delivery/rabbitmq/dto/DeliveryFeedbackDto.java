package com.ydh.hello_delivery.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryFeedbackDto {

    private Long deliveryId;
    private MessageType messageType;
}
