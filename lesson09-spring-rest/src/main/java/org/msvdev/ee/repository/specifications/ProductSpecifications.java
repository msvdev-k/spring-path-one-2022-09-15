package org.msvdev.ee.repository.specifications;

import org.msvdev.ee.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;


public class ProductSpecifications {

    public static Specification<Product> costGreaterThanOrEqualTo(BigDecimal minCost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), minCost);
    }

    public static Specification<Product> costLessThanOrEqualTo(BigDecimal maxCost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxCost);
    }


    public static Specification<Product> titleLike(String title) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", title));
    }
}