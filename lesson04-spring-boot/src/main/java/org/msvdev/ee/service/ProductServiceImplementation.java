package org.msvdev.ee.service;

import org.msvdev.ee.entity.Product;
import org.msvdev.ee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;


    @Autowired
    public ProductServiceImplementation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }


    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productRepository.findById(id));
    }


    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            productRepository.insert(product);

        } else {
            productRepository.update(product);
        }
    }


    @Override
    public void deleteById(Long id) {
        productRepository.delete(id);
    }
}