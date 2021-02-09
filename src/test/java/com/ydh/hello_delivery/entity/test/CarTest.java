package com.ydh.hello_delivery.entity.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CarTest {

    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void hello() throws Exception {
        Person person = new Person();

        Car car = new Car();
        car.setName("마세라티");

        car.setPerson(person);
        person.setCar(car);

        em.persist(person);
        em.persist(car);

//        em.flush();
//        em.clear();

        Car findCar = em.find(Car.class, 2L);
        findCar.setName("포르쉐");

//        em.flush();
//        em.clear();

        Person findPerson = em.find(Person.class, 1L);

        Car car1 = findPerson.getCar();
        System.out.println("car1 = " + car1);

    }

}