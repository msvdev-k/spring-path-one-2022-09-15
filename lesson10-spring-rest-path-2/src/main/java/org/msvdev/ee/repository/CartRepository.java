package org.msvdev.ee.repository;

import org.msvdev.ee.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {

    Long countByProductIdIs(Long productId);
}