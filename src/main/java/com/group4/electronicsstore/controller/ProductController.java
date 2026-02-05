package com.group4.electronicsstore.controller;

import com.group4.electronicsstore.dto.ProductDTO;
import com.group4.electronicsstore.dto.response.ApiResponse;
import com.group4.electronicsstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public ApiResponse<Page<ProductDTO>> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<Page<ProductDTO>>builder()
                .message("Get all products successfully")
                .result(productService.getAllProducts(page, size))
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<ProductDTO> getProductById(@PathVariable long id) {
        return ApiResponse.<ProductDTO>builder()
                .message("Get product detail successfully")
                .result(productService.getProductById(id))
                .build();
    }
    @GetMapping("/search")
    public ApiResponse<Page<ProductDTO>> searchProducts(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<Page<ProductDTO>>builder()
                .message("Search products successfully")
                .result(productService.searchByName(name, page, size))
                .build();
    }
    @GetMapping("/category/{categoryId}")
    public ApiResponse<Page<ProductDTO>> filterByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<Page<ProductDTO>>builder()
                .message("Filter products by category successfully")
                .result(productService.filterByCategory(categoryId, page, size))
                .build();
    }
}
