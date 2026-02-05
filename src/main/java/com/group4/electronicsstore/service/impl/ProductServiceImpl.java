package com.group4.electronicsstore.service.impl;

import com.group4.electronicsstore.dto.ProductDTO;
import com.group4.electronicsstore.entity.Product;
import com.group4.electronicsstore.exception.AppException;
import com.group4.electronicsstore.exception.ErrorCode;
import com.group4.electronicsstore.repository.ProductRepository;
import com.group4.electronicsstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<ProductDTO> getAllProducts( int page,  int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByActiveTrue(pageable);
        if(productPage.isEmpty()) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        return productPage.map(product -> modelMapper.map(product, ProductDTO.class));
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public Page<ProductDTO> searchByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByNameContainingIgnoreCase(name, pageable);
        if(products.isEmpty()) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        return products.map(product -> modelMapper.map(product, ProductDTO.class));
    }

    @Override
    public Page<ProductDTO> filterByCategory(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByCategoryId(categoryId, pageable)
                .map(product -> modelMapper.map(product, ProductDTO.class));
    }
}
