package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.CategoryRegisterRequest;
import com.nhnacademy.springjpa.entity.Category;
import com.nhnacademy.springjpa.repository.CategoryRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Page<Category> getCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public void register(CategoryRegisterRequest categoryRegisterRequest) {
        Category category = new Category();

        category.setCategoryName(categoryRegisterRequest.getCategoryName());

        categoryRepository.save(category);
    }

    public void deleteByCategoryId(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
