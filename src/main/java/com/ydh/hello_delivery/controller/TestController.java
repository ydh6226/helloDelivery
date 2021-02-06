package com.ydh.hello_delivery.controller;

import com.ydh.hello_delivery.rabbitmq.dto.DeliveryFeedbackDto;
import com.ydh.hello_delivery.rabbitmq.dto.MessageType;
import com.ydh.hello_delivery.rabbitmq.sender.DeliverySender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final DeliverySender deliverySender;

    @GetMapping("/hello")
    @ResponseBody
    public void test() {
        DeliveryFeedbackDto dto = new DeliveryFeedbackDto();
        dto.setMessageType(MessageType.ARRIVAL);
        deliverySender.send(dto);
    }
}
