package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetail.Pk> {
    int deleteByPk_OrderIdAndPk_ProductId(Integer orderId, Integer productId);
}
