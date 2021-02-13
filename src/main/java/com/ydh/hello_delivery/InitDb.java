package com.ydh.hello_delivery;

import com.ydh.hello_delivery.entity.Address;
import com.ydh.hello_delivery.entity.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import static com.ydh.hello_delivery.entity.Delivery.createDelivery;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitDelivery initDelivery;

//    @PostConstruct
    public void dbInit() {
        initDelivery.init();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitDelivery {
        private final EntityManager em;

        public void init() {
            for (long i = 200L; i < 400L; i++) {
                em.persist(Delivery.createDelivery(i, "", "상품" + i, new Address("", "", "")));
            }
        }
    }
}
