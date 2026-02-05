package com.group4.electronicsstore.service;

import com.group4.electronicsstore.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductDTO> getAllProducts(int page, int size);
    ProductDTO getProductById(Long id);
    Page<ProductDTO> searchByName(String name, int page, int size);
    Page<ProductDTO> filterByCategory(Long categoryId, int page, int size);
}
