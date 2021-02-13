package com.ydh.hello_delivery.repository;

import com.ydh.hello_delivery.entity.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomDeliveryRepository  {

     Page<Delivery> findBySearchWithPaging(Pageable pageable);
}
