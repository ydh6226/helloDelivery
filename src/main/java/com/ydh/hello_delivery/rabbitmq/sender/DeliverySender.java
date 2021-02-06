package com.ydh.hello_delivery.rabbitmq.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ydh.hello_delivery.rabbitmq.dto.DeliveryFeedbackDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliverySender {

    private final RabbitMessagingTemplate template;

    private final String queueName = "delivery2shop.queue";
    private final String exchangeName = "delivery2shop.direct";
    private final String routingKey = "delivery-feedback";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    public void send(DeliveryFeedbackDto dto) {
        try {
            String deliveryJsonString = new ObjectMapper().writeValueAsString(dto);
            template.convertAndSend(exchangeName, routingKey, deliveryJsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
