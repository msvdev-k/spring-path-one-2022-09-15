package org.msvdev.ee.service;


import org.msvdev.ee.entity.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartService {

    List<CartItem> findAll();

    Optional<CartItem> findById(Long id);

    CartItem save(CartItem cartItem);

    void deleteById(Long id);

    Long countByProductId(Long productId);

}