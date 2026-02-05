package com.group4.electronicsstore.controller;

import com.group4.electronicsstore.dto.ProductDTO;
import com.group4.electronicsstore.dto.request.ProductRequest;
import com.group4.electronicsstore.dto.response.ApiResponse;
import com.group4.electronicsstore.entity.Product;
import com.group4.electronicsstore.service.AdminProductService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/products")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ProductAdminController {

    private final AdminProductService adminProductService;

    @GetMapping
    public ApiResponse<List<Product>> getAll() {
        return ApiResponse.<List<Product>>builder()
                .message("Get all products successfully")
                .result(adminProductService.getAll())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<Product> getById(@PathVariable Long id) {
        return ApiResponse.<Product>builder()
                .message("Get product detail successfully")
                .result(adminProductService.getById(id))
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<Product>> searchByName(@RequestParam String name) {
        return ApiResponse.<List<Product>>builder()
                .message("Search products successfully")
                .result(adminProductService.searchByName(name))
                .build();
    }

    @PostMapping
    public ApiResponse<ProductDTO> create(@RequestBody @Valid ProductRequest product) {
        return ApiResponse.<ProductDTO>builder()
                .message("Create product successfully")
                .result(adminProductService.createProduct(product))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductDTO> update(@PathVariable Long id, @RequestBody @Valid ProductRequest product) {
        return ApiResponse.<ProductDTO>builder()
                .message("Update product successfully")
                .result(adminProductService.update(id, product))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        adminProductService.delete(id);
        return ApiResponse.<Void>builder()
                .message("Delete product successfully")
                .build();
    }
}
