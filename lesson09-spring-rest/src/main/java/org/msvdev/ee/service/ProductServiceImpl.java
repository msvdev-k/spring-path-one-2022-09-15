package org.msvdev.ee.service;

import org.msvdev.ee.dto.ProductDto;
import org.msvdev.ee.entity.Product;
import org.msvdev.ee.repository.ProductRepository;
import org.msvdev.ee.repository.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(ProductDto::new);
    }


    @Override
    public Page<ProductDto> findWithFilter(Integer page, Integer pageSize,
                                           String title,
                                           BigDecimal minCost,
                                           BigDecimal maxCost,
                                           String sortField, String sortOrder) {

        Specification<Product> spec = Specification.where(null);

        if (title != null && !title.isBlank()) {
            spec = spec.and(ProductSpecifications.titleLike(title));
        }
        if (minCost != null) {
            spec = spec.and(ProductSpecifications.costGreaterThanOrEqualTo(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductSpecifications.costLessThanOrEqualTo(maxCost));
        }

        PageRequest pageRequest;

        if (sortField != null && !sortField.isBlank()) {
            pageRequest = PageRequest.of(page - 1, pageSize,
                    Sort.by(Sort.Direction.fromString(sortOrder), sortField)
            );

        } else {
            pageRequest = PageRequest.of(page - 1, pageSize);
        }

        return productRepository.findAll(spec, pageRequest).map(ProductDto::new);
    }


    @Override
    @Transactional
    public ProductDto save(ProductDto productDto) {
        return new ProductDto(productRepository.save(new Product(productDto)));
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}