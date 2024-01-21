package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, ProductCategory.Pk> {
    int countByPk_ProductIdAndPk_CategoryId(Integer productId, Integer categoryId);
    int deleteByPk_ProductId(Integer productId);

}
