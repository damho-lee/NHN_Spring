package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    int countByUser_UserId(String userId);
    int countByOrderId(Integer orderId);
    List<Order> findOrdersByUser_UserId(String userId);
    Order findOrderByOrderId(Integer orderId);
}
