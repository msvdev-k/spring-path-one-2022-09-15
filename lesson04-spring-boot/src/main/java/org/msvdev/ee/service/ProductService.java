package org.msvdev.ee.service;

import org.msvdev.ee.entity.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    void save(Product product);

    void deleteById(Long id);
}