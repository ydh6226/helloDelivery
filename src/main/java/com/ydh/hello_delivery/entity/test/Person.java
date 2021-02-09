package com.ydh.hello_delivery.entity.test;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Person {

    @Id @GeneratedValue
    @Column(name = "person_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
    private Car car;
}
