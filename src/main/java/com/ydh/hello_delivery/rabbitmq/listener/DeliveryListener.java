package com.ydh.hello_delivery.rabbitmq.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ydh.hello_delivery.rabbitmq.dto.DeliveryDto;
import com.ydh.hello_delivery.rabbitmq.dto.DeliveryFeedbackDto;
import com.ydh.hello_delivery.service.DeliveryService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeliveryListener {

    private final DeliveryService deliveryService;

    private final String queueName = "shop2delivery.queue";

    @RabbitListener(queues = {queueName})
    public void processMessage(String deliveryJsonString) {
        try {
            System.out.println("orderJsonString = " + deliveryJsonString);
            DeliveryDtoWrapper dtoWrapper = new ObjectMapper().readValue(deliveryJsonString, DeliveryDtoWrapper.class);

            if (dtoWrapper.count == 1) {
                deliveryService.registerOne(dtoWrapper.getDtos().get(0));
                return;
            }

            deliveryService.registerAll(dtoWrapper.getDtos());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    static class DeliveryDtoWrapper {
        private int count;
        List<DeliveryDto> dtos;
    }
}
