package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.ShoppingCart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    List<ShoppingCart> findShoppingCartsByUser_UserId(String userId);
    int countByRecordId(Integer recordId);
    int countByUser_UserId(String userId);
    int countByUser_UserIdAndProduct_ProductId(String userId, Integer productId);
}
