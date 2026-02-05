package com.group4.electronicsstore.service;

import com.group4.electronicsstore.dto.request.CategoryCreationRequest;
import com.group4.electronicsstore.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CategoryService {
    Page<Category> getAllCategories(int page,int size);
    Page<Category> getAllCategoriesLike(String categoryName, int page, int size);
    Category getCategoryById(Long id);
    public CategoryCreationRequest createCategory(CategoryCreationRequest category);
    Category updateCategory(Long id, Category categoryDetails);
    void deleteCategory(Long id);
}