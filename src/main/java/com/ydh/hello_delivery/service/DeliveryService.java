package com.ydh.hello_delivery.service;

import com.ydh.hello_delivery.controller.PaginationDto;
import com.ydh.hello_delivery.entity.Delivery;
import com.ydh.hello_delivery.exception.NoSuchDelivery;
import com.ydh.hello_delivery.rabbitmq.dto.DeliveryDto;
import com.ydh.hello_delivery.rabbitmq.dto.DeliveryFeedbackDto;
import com.ydh.hello_delivery.rabbitmq.dto.MessageType;
import com.ydh.hello_delivery.rabbitmq.sender.DeliverySender;
import com.ydh.hello_delivery.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliverySender deliverySender;

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    public Page<Delivery> findAllWithPaging(PaginationDto dto) {
        PageRequest pageRequest = PageRequest.of(dto.getPage(), dto.getSize());
        Page<Delivery> result = deliveryRepository.findBySearchWithPaging(pageRequest);
        return result;
    }

    public Page<Delivery> findAllWithPaging() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Delivery> result = deliveryRepository.findBySearchWithPaging(pageRequest);
        return result;
    }

    @Transactional
    public Long registerOne(DeliveryDto dto) {
        Delivery delivery = deliveryRepository.save(Delivery.createDelivery(dto.getDeliveryId(), dto.getMemberName(),
                dto.getItemName(), dto.getAddress()));
        return delivery.getId();
    }

    @Transactional
    public List<Long> registerAll(List<DeliveryDto> dtos) {
        List<Delivery> deliveries = dtos.stream()
                .map(d -> Delivery.createDelivery(d.getDeliveryId(), d.getMemberName(),
                        d.getItemName(), d.getAddress())).collect(Collectors.toList());

        return deliveryRepository.saveAll(deliveries).stream()
                .map(Delivery::getId).collect(Collectors.toList());
    }

    @Transactional
    public Long startDelivery(Long id) {
        Delivery delivery = deliveryRepository.findById(id).orElseThrow(this::noSuchDelivery);
        delivery.startDelivery();

        try {
            deliverySender.send(new DeliveryFeedbackDto(id, MessageType.SHIPPED));
        } catch (Exception e) {
            delivery.startDeliveryRollback();
            e.printStackTrace();
        }
        return delivery.getId();
    }

    @Transactional
    public Long endDelivery(Long id) {
        Delivery delivery = deliveryRepository.findById(id).orElseThrow(this::noSuchDelivery);
        delivery.endDelivery();

        try {
            deliverySender.send(new DeliveryFeedbackDto(id, MessageType.COMP));
        } catch (Exception e) {
            delivery.endDeliveryRollback();
            e.printStackTrace();
        }
        return delivery.getId();
    }

    private NoSuchDelivery noSuchDelivery() {
        return new NoSuchDelivery("can't find that Delivery");
    }
}
