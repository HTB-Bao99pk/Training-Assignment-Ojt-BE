package com.group4.electronicsstore.service;

import com.group4.electronicsstore.dto.ProductDTO;
import com.group4.electronicsstore.dto.request.ProductRequest;
import com.group4.electronicsstore.entity.Product;

import java.util.List;

public interface AdminProductService {
    List<Product> getAll();
    Product getById(Long id);
    List<Product> searchByName(String name);
    ProductDTO createProduct(ProductRequest request);
    ProductDTO update(Long id, ProductRequest product);
    void delete(Long id);
}
