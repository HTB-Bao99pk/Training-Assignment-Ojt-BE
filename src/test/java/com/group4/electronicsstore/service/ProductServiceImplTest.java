package com.group4.electronicsstore.service;

import com.group4.electronicsstore.dto.ProductDTO;
import com.group4.electronicsstore.entity.Category;
import com.group4.electronicsstore.entity.Product;
import com.group4.electronicsstore.exception.AppException;
import com.group4.electronicsstore.exception.ErrorCode;
import com.group4.electronicsstore.repository.ProductRepository;
import com.group4.electronicsstore.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDTO productDTO;
    private Category category;
    private List<Product> productList;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(1000.0);
        product.setDescription("Gaming laptop");
        product.setStockQuantity(10);
        product.setActive(true);
        product.setCategory(category);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Mouse");
        product2.setPrice(50.0);
        product2.setDescription("Wireless mouse");
        product2.setStockQuantity(20);
        product2.setActive(true);
        product2.setCategory(category);

        productList = Arrays.asList(product, product2);

        productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Laptop");
        productDTO.setPrice(1000.0);
        productDTO.setDescription("Gaming laptop");
        productDTO.setStockQuantity(10);
        productDTO.setActive(true);
        productDTO.setCategoryName("Electronics");
    }

    @Test
    void getAllProducts_ShouldReturnPageOfProductDTOs() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(productList, pageable, productList.size());

        when(productRepository.findByActiveTrue(pageable)).thenReturn(productPage);
        when(modelMapper.map(any(Product.class), eq(ProductDTO.class))).thenReturn(productDTO);

        Page<ProductDTO> result = productService.getAllProducts(0, 10);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(productRepository, times(1)).findByActiveTrue(pageable);
    }

    @Test
    void getAllProducts_WhenNoProducts_ShouldThrowException() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> emptyPage = Page.empty(pageable);

        when(productRepository.findByActiveTrue(pageable)).thenReturn(emptyPage);

        AppException exception = assertThrows(AppException.class,
                () -> productService.getAllProducts(0, 10));

        assertEquals(ErrorCode.PRODUCT_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    void getProductById_WithValidId_ShouldReturnProductDTO() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(modelMapper.map(product, ProductDTO.class)).thenReturn(productDTO);

        ProductDTO result = productService.getProductById(1L);

        assertEquals("Laptop", result.getName());
    }

    @Test
    void getProductById_WithInvalidId_ShouldThrowException() {
        when(productRepository.findById(999L)).thenReturn(Optional.empty());

        AppException exception = assertThrows(AppException.class,
                () -> productService.getProductById(999L));

        assertEquals(ErrorCode.PRODUCT_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    void searchByName_WithMatchingName_ShouldReturnProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product), pageable, 1);

        when(productRepository.findByNameContainingIgnoreCase("Laptop", pageable))
                .thenReturn(productPage);
        when(modelMapper.map(any(Product.class), eq(ProductDTO.class)))
                .thenReturn(productDTO);

        Page<ProductDTO> result = productService.searchByName("Laptop", 0, 10);

        assertEquals(1, result.getTotalElements());
    }

    @Test
    void searchByName_WithNoMatch_ShouldThrowException() {
        Pageable pageable = PageRequest.of(0, 10);
        when(productRepository.findByNameContainingIgnoreCase("None", pageable))
                .thenReturn(Page.empty(pageable));

        assertThrows(AppException.class,
                () -> productService.searchByName("None", 0, 10));
    }

    @Test
    void filterByCategory_WithValidCategoryId_ShouldReturnProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(productList, pageable, productList.size());

        when(productRepository.findByCategoryId(1L, pageable)).thenReturn(productPage);
        when(modelMapper.map(any(Product.class), eq(ProductDTO.class))).thenReturn(productDTO);

        Page<ProductDTO> result = productService.filterByCategory(1L, 0, 10);

        assertEquals(2, result.getTotalElements());
    }

    @Test
    void filterByCategory_WithNoProducts_ShouldReturnEmptyPage() {
        Pageable pageable = PageRequest.of(0, 10);
        when(productRepository.findByCategoryId(999L, pageable))
                .thenReturn(Page.empty(pageable));

        Page<ProductDTO> result = productService.filterByCategory(999L, 0, 10);

        assertEquals(0, result.getTotalElements());
    }
}
