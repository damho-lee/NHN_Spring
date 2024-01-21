package com.nhnacademy.springjpa.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "point_usage")
public class PointUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_usage_id")
    private Integer pointUsageId;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "before_point")
    private Integer beforePoint;

    @Column
    private Integer variance;

    @Column(name = "after_point")
    private Integer afterPoint;
}
