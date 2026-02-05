package com.group4.electronicsstore.service.impl;

import com.group4.electronicsstore.dto.request.CategoryCreationRequest;
import com.group4.electronicsstore.entity.Category;
import com.group4.electronicsstore.exception.AppException;
import com.group4.electronicsstore.exception.ErrorCode;
import com.group4.electronicsstore.repository.CategoryRepository;
import com.group4.electronicsstore.repository.ProductRepository;
import com.group4.electronicsstore.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    @Override
    public Page<Category> getAllCategories(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage;
    }

    @Override
    public Page<Category> getAllCategoriesLike(String categoryName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> result = categoryRepository.findByNameContainingIgnoreCase(categoryName, pageable);
        if (result.isEmpty()) {
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        return result;
    }

    @Override
    public CategoryCreationRequest createCategory(CategoryCreationRequest category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new AppException(ErrorCode.CATEGORY_NAME_EXISTED);
        }
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        categoryRepository.save(newCategory);
        return modelMapper.map(category, CategoryCreationRequest.class);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    @Override
    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        category.setName(categoryDetails.getName());
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        productRepository.deleteByCategoryId(id);
        categoryRepository.deleteById(id);
    }
}