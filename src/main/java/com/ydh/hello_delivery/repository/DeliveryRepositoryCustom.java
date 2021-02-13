package com.ydh.hello_delivery.repository;

import com.ydh.hello_delivery.controller.DeliverySearch;
import com.ydh.hello_delivery.entity.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryRepositoryCustom {

     Page<Delivery> findBySearchWithPaging(DeliverySearch search, Pageable pageable);
}
