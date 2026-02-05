package com.group4.electronicsstore.controller;

import com.group4.electronicsstore.dto.request.CategoryCreationRequest;
import com.group4.electronicsstore.dto.response.ApiResponse;
import com.group4.electronicsstore.entity.Category;
import com.group4.electronicsstore.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/categories")
@SecurityRequirement(name = "bearerAuth")
public class CategoryAdminController {

    private final CategoryService categoryService;


    public CategoryAdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ApiResponse<Page<Category>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<Page<Category>>builder()
                .message("Get all categories successfully")
                .result(categoryService.getAllCategories(page, size))
                .build();
    }

    @GetMapping("/name/{searchName}")
    public ApiResponse<Page<Category>> searchByName(@PathVariable String searchName, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ApiResponse
                .<Page<Category>>builder()
                .message("Get categories by name successfully")
                .result(categoryService.getAllCategoriesLike(searchName,page, size))
                .build();
    }

    @GetMapping("/id/{id}")
    public ApiResponse<Category> getById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return ApiResponse.<Category>builder()
                .message("Get category detail successfully")
                .result(category)
                .build();
    }

    @PostMapping
    public ApiResponse<CategoryCreationRequest> create(@Valid @RequestBody CategoryCreationRequest category) {
        return ApiResponse.<CategoryCreationRequest>builder()
                .message("Create category successfully")
                .result(categoryService.createCategory(category))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<Category> update(@PathVariable Long id, @Valid @RequestBody Category categoryDetails) {
        return ApiResponse.<Category>builder()
                .message("Update category successfully")
                .result(categoryService.updateCategory(id, categoryDetails))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ApiResponse.<Void>builder()
                .message("Delete category successfully")
                .build();
    }
}