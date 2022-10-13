package org.msvdev.ee.service;

import org.msvdev.ee.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    List<Product> findAllWithCostFilter(Optional<BigDecimal> costMin, Optional<BigDecimal> costMax);

    void save(Product product);

    void deleteById(Long id);
}