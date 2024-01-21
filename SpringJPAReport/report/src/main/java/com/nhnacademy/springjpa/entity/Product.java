package com.nhnacademy.springjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "model_number")
    private String modelNumber;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "product_image")
    private String image;

    @Column(name = "unit_cost")
    private Double unitCost;

    @Column
    private Integer quantity;

    @Column
    private String description;
}
