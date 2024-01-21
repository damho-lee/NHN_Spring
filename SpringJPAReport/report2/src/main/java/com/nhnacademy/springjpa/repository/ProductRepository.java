package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    int countByProductId(Integer productId);
}
