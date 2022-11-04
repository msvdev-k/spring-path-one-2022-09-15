package org.msvdev.ee.service;

import lombok.RequiredArgsConstructor;
import org.msvdev.ee.entity.Product;
import org.msvdev.ee.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }


    public Page<Product> findWithFilter(Integer page, Integer pageSize) {
        return productRepository.findAll(PageRequest.of(page - 1, pageSize));
    }


    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}