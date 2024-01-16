package com.nhnacademy.springjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "detail")
    private String detail;
}
