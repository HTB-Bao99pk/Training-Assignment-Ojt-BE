package com.group4.electronicsstore.repository;

import com.group4.electronicsstore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByActiveTrue(Pageable pageable);
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
    void deleteByCategoryId(Long categoryId);
}