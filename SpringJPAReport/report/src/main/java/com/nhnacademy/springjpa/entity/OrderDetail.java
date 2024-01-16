package com.nhnacademy.springjpa.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    private Pk pk;

    @Column
    private Integer quantity;

    @Column(name = "unit_cost")
    private Double unitCost;

    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "order_id")
        private Integer orderId;

        @Column(name = "product_id")
        private Integer productId;
    }
}
