package com.ydh.hello_delivery.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ydh.hello_delivery.entity.Delivery;
import com.ydh.hello_delivery.entity.QDelivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.ydh.hello_delivery.entity.QDelivery.delivery;

public class DeliveryRepositoryImpl implements CustomDeliveryRepository {

    private final JPAQueryFactory query;

    public DeliveryRepositoryImpl(EntityManager em) {
        query = new JPAQueryFactory(em);
    }

    @Override
    public Page<Delivery> findBySearchWithPaging(Pageable pageable) {
        QueryResults<Delivery> result = query.selectFrom(delivery)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
