package com.ydh.hello_delivery.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ydh.hello_delivery.controller.DeliverySearch;
import com.ydh.hello_delivery.entity.Delivery;
import com.ydh.hello_delivery.entity.DeliveryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;

import static com.ydh.hello_delivery.entity.QDelivery.delivery;

public class DeliveryRepositoryImpl extends QuerydslRepositorySupport implements DeliveryRepositoryCustom {

    private final JPAQueryFactory query;

    public DeliveryRepositoryImpl(EntityManager em) {
        super(Delivery.class);
        query = new JPAQueryFactory(em);
    }

    @Override
    public Page<Delivery> findBySearchWithPaging(DeliverySearch search, Pageable pageable) {
        QueryResults<Delivery> result = query.selectFrom(delivery)
                .where(memberNameLike(search.getMemberName()),
                        statusEq(search.getDeliveryStatus()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression memberNameLike(String memberName) {
        if (memberName == null) {
            return null;
        }
        return delivery.memberName.contains(memberName);
    }

    private BooleanExpression statusEq(DeliveryStatus status) {
        if (status == null) {
            return null;
        }
        return delivery.deliveryStatus.eq(status);
    }
}
