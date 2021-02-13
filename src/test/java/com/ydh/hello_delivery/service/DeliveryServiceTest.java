package com.ydh.hello_delivery.service;

import com.ydh.hello_delivery.entity.Delivery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryServiceTest {

    @Autowired
    DeliveryService deliveryService;

    @Test
    @DisplayName("페이징 테스트")
    public void paging() throws Exception {
        Page<Delivery> result = deliveryService.findAllWithPaging();
        System.out.println(result.getTotalPages());
        System.out.println(result.getNumber());
        System.out.println(result.getSize());
        result.getContent().forEach(r -> System.out.println(r.getItemName()));
    }

}