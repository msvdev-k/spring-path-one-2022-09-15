package org.msvdev.ee.service;

import org.msvdev.ee.entity.Product;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface ProductService {

    Optional<Product> findById(Long id);

    Page<Product> findAllWithPageFilter(Optional<Integer> page, Optional<Integer> pageSize);

    void save(Product product);

    void deleteById(Long id);
}