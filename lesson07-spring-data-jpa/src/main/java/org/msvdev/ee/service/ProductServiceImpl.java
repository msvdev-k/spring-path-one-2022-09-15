package org.msvdev.ee.service;

import org.msvdev.ee.entity.Product;
import org.msvdev.ee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }


    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAllWithCostFilter(Optional<BigDecimal> costMin, Optional<BigDecimal> costMax) {

        BigDecimal min = costMin.orElseGet(() -> new BigDecimal(0));
        BigDecimal max = costMax.orElseGet(() -> new BigDecimal(999999999999L));

        return productRepository.findAllByCostBetween(min, max);
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}