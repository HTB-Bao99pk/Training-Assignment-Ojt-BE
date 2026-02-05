package com.group4.electronicsstore.service.impl;

import com.group4.electronicsstore.dto.ProductDTO;
import com.group4.electronicsstore.dto.request.ProductRequest;
import com.group4.electronicsstore.entity.Category;
import com.group4.electronicsstore.entity.Product;
import com.group4.electronicsstore.exception.AppException;
import com.group4.electronicsstore.exception.ErrorCode;
import com.group4.electronicsstore.repository.CategoryRepository;
import com.group4.electronicsstore.repository.ProductRepository;
import com.group4.electronicsstore.service.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    public List<Product> searchByName(String name) {
        Page<Product> page =
                productRepository.findByNameContainingIgnoreCase(
                        name,
                        Pageable.unpaged()
                );
        return page.getContent();
    }

    @Override
    public ProductDTO createProduct(ProductRequest request) {
        // Check trùng tên
        if (productRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.PRODUCT_NAME_EXISTED);
        }

        // Tìm Category theo id
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        //Map thủ công vì ko thể map tự động bằng modelmapper được
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setImage(request.getImage());
        product.setStockQuantity(request.getStockQuantity());
        product.setActive(request.isActive());
        product.setCategory(category);

        // 4. Lưu và trả về
        return modelMapper.map(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public ProductDTO update(Long id, ProductRequest request) {
        //Tìm sản phẩm cần update
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        //Tìm category (theo id từ request)
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        //Check trùng tên
        //Chỉ ném lỗi nếu có tên trùng ở id khác
        if (productRepository.existsByNameAndIdNot(request.getName(), id)) {
            throw new AppException(ErrorCode.PRODUCT_NAME_EXISTED);
        }

        //Cập nhập dữ liệu
        existingProduct.setName(request.getName());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setImage(request.getImage());
        existingProduct.setStockQuantity(request.getStockQuantity());
        existingProduct.setActive(request.isActive());

        existingProduct.setCategory(category);

        //Lưu và trả về DTO
        return modelMapper.map(productRepository.save(existingProduct), ProductDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        productRepository.deleteById(id);
    }
}
