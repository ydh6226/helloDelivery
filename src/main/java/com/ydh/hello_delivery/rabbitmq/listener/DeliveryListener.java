package com.ydh.hello_delivery.rabbitmq.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ydh.hello_delivery.rabbitmq.dto.DeliveryDto;
import com.ydh.hello_delivery.rabbitmq.dto.DeliveryFeedbackDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryListener {

    private final String queueName = "shop2delivery.queue";

    @RabbitListener(queues = {queueName})
    public void processMessage(String orderJsonString) {
        try {
            DeliveryDto dto = new ObjectMapper().readValue(orderJsonString, DeliveryDto.class);
            System.out.println(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    static class DeliveryDtoWrapper {
        private int count;
        List<DeliveryDto> deliveryDtos;
    }
}
