package org.msvdev.ee.service;

import lombok.RequiredArgsConstructor;
import org.msvdev.ee.entity.Product;
import org.msvdev.ee.repository.ProductRepository;
import org.msvdev.ee.repository.specifications.ProductSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;



    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }


    @Override
    public Page<Product> findWithFilter(Integer page, Integer pageSize,
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

        return productRepository.findAll(spec, pageRequest);
    }


    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}