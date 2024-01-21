package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.CategoryRegisterRequest;
import com.nhnacademy.springjpa.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/category/register")
public class CategoryRegisterController {
    private final CategoryService categoryService;

    public CategoryRegisterController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getCategoryRegisterForm() {
        return "categoryRegisterForm";
    }

    @PostMapping
    public String categoryRegister(@ModelAttribute CategoryRegisterRequest categoryRegisterRequest) {
        log.info("categoryName : {}", categoryRegisterRequest.getCategoryName());

        categoryService.register(categoryRegisterRequest);

        return "redirect:/category";
    }
}
