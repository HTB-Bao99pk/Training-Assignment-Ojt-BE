//package com.group4.electronicsstore.service;
//
//import com.group4.electronicsstore.entity.Category;
//import com.group4.electronicsstore.exception.AppException;
//import com.group4.electronicsstore.exception.ErrorCode;
//import com.group4.electronicsstore.repository.CategoryRepository;
//import com.group4.electronicsstore.service.impl.CategoryServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class CategoryServiceImplTest {
//
//    @Mock
//    private CategoryRepository categoryRepository;
//
//    @InjectMocks
//    private CategoryServiceImpl categoryService;
//
//    private Category category;
//    private List<Category> categoryList;
//
//    @BeforeEach
//    void setUp() {
//        category = new Category();
//        category.setId(1L);
//        category.setName("Electronics");
//
//        Category category2 = new Category();
//        category2.setId(2L);
//        category2.setName("Books");
//
//        categoryList = Arrays.asList(category, category2);
//    }
//
//    @Test
//    void getAllCategories_ShouldReturnPageOfCategories() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Category> expectedPage = new PageImpl<>(categoryList, pageable, categoryList.size());
//
//        when(categoryRepository.findAll(pageable)).thenReturn(expectedPage);
//
//        Page<Category> result = categoryService.getAllCategories(0, 10);
//
//        assertNotNull(result);
//        assertEquals(2, result.getTotalElements());
//        assertEquals("Electronics", result.getContent().get(0).getName());
//        verify(categoryRepository, times(1)).findAll(pageable);
//    }
//
//    @Test
//    void getAllCategoriesLike_WithMatchingName_ShouldReturnCategories() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Category> expectedPage = new PageImpl<>(Arrays.asList(category), pageable, 1);
//
//        when(categoryRepository.findByNameContainingIgnoreCase("Electronics", pageable))
//                .thenReturn(expectedPage);
//
//        Page<Category> result = categoryService.getAllCategoriesLike("Electronics", 0, 10);
//
//        assertNotNull(result);
//        assertEquals(1, result.getTotalElements());
//        assertEquals("Electronics", result.getContent().get(0).getName());
//        verify(categoryRepository, times(1)).findByNameContainingIgnoreCase("Electronics", pageable);
//    }
//
//    @Test
//    void getAllCategoriesLike_WithNoMatch_ShouldThrowException() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Category> emptyPage = Page.empty(pageable);
//
//        when(categoryRepository.findByNameContainingIgnoreCase("NonExistent", pageable))
//                .thenReturn(emptyPage);
//
//        AppException exception = assertThrows(AppException.class, () -> {
//            categoryService.getAllCategoriesLike("NonExistent", 0, 10);
//        });
//
//        assertEquals(ErrorCode.CATEGORY_NOT_FOUND, exception.getErrorCode());
//        verify(categoryRepository, times(1)).findByNameContainingIgnoreCase("NonExistent", pageable);
//    }
//
//    @Test
//    void createCategory_ShouldReturnSavedCategory() {
//        when(categoryRepository.save(any(Category.class))).thenReturn(category);
//
//        Category result = categoryService.createCategory(category);
//
//        assertNotNull(result);
//        assertEquals(1L, result.getId());
//        assertEquals("Electronics", result.getName());
//        verify(categoryRepository, times(1)).save(category);
//    }
//
//    @Test
//    void getCategoryById_WithValidId_ShouldReturnCategory() {
//        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
//
//        Category result = categoryService.getCategoryById(1L);
//
//        assertNotNull(result);
//        assertEquals(1L, result.getId());
//        assertEquals("Electronics", result.getName());
//        verify(categoryRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void getCategoryById_WithInvalidId_ShouldThrowException() {
//        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());
//
//        AppException exception = assertThrows(AppException.class, () -> {
//            categoryService.getCategoryById(999L);
//        });
//
//        assertEquals(ErrorCode.CATEGORY_NOT_FOUND, exception.getErrorCode());
//        verify(categoryRepository, times(1)).findById(999L);
//    }
//
//    @Test
//    void updateCategory_WithValidId_ShouldReturnUpdatedCategory() {
//        Category updatedDetails = new Category();
//        updatedDetails.setName("Updated Electronics");
//
//        Category updatedCategory = new Category();
//        updatedCategory.setId(1L);
//        updatedCategory.setName("Updated Electronics");
//
//        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
//        when(categoryRepository.save(any(Category.class))).thenReturn(updatedCategory);
//
//        Category result = categoryService.updateCategory(1L, updatedDetails);
//
//        assertNotNull(result);
//        assertEquals("Updated Electronics", result.getName());
//        verify(categoryRepository, times(1)).findById(1L);
//        verify(categoryRepository, times(1)).save(any(Category.class));
//    }
//
//    @Test
//    void updateCategory_WithInvalidId_ShouldThrowException() {
//        Category updatedDetails = new Category();
//        updatedDetails.setName("Updated Electronics");
//
//        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());
//
//        AppException exception = assertThrows(AppException.class, () -> {
//            categoryService.updateCategory(999L, updatedDetails);
//        });
//
//        assertEquals(ErrorCode.CATEGORY_NOT_FOUND, exception.getErrorCode());
//        verify(categoryRepository, times(1)).findById(999L);
//        verify(categoryRepository, never()).save(any(Category.class));
//    }
//
//    @Test
//    void deleteCategory_WithValidId_ShouldDeleteSuccessfully() {
//        when(categoryRepository.existsById(1L)).thenReturn(true);
//        doNothing().when(categoryRepository).deleteById(1L);
//
//        categoryService.deleteCategory(1L);
//
//        verify(categoryRepository, times(1)).existsById(1L);
//        verify(categoryRepository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    void deleteCategory_WithInvalidId_ShouldThrowException() {
//        when(categoryRepository.existsById(999L)).thenReturn(false);
//
//        AppException exception = assertThrows(AppException.class, () -> {
//            categoryService.deleteCategory(999L);
//        });
//
//        assertEquals(ErrorCode.CATEGORY_NOT_FOUND, exception.getErrorCode());
//        verify(categoryRepository, times(1)).existsById(999L);
//        verify(categoryRepository, never()).deleteById(any());
//    }
//}
