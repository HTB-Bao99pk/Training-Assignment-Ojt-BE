package com.group4.electronicsstore.controller;

import com.group4.electronicsstore.dto.response.ApiResponse;
import com.group4.electronicsstore.entity.Category;
import com.group4.electronicsstore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @GetMapping
    public ApiResponse<List<Category>> getAllCategories() {
        return ApiResponse.<List<Category>>builder()
                .message("Get all categories successfully")
                .result(categoryRepository.findAll())
                .build();
    }
}
