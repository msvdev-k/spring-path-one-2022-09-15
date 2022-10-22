package org.msvdev.ee.service;

import org.msvdev.ee.entity.Product;
import org.msvdev.ee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }


    @Override
    public Page<Product> findAllWithPageFilter(Optional<Integer> page, Optional<Integer> pageSize) {
        PageRequest pageRequest = PageRequest.of(page.orElse(1) - 1, pageSize.orElse(10));
        return productRepository.findAll(pageRequest);
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