package com.ydh.hello_delivery.rabbitmq.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ydh.hello_delivery.rabbitmq.dto.DeliveryDelegateDto;
import com.ydh.hello_delivery.service.DeliveryService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliveryListener {

    private final DeliveryService deliveryService;

    private final String queueName = "shop2delivery.queue";

    @RabbitListener(queues = {queueName})
    public void processMessage(String deliveryJsonString) {
        try {
            System.out.println("orderJsonString = " + deliveryJsonString);
            DeliveryInfoJson deliveryInfoJson = new ObjectMapper().readValue(deliveryJsonString, DeliveryInfoJson.class);

            if (deliveryInfoJson.count == 1) {
                deliveryService.registerOne(deliveryInfoJson.getDeliveryInfos().get(0));
                return;
            }

            deliveryService.registerAll(deliveryInfoJson.getDeliveryInfos());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    static class DeliveryInfoJson {
        private int count;
        List<DeliveryDelegateDto> deliveryInfos;
    }
}
