package org.msvdev.ee.service;

import lombok.RequiredArgsConstructor;
import org.msvdev.ee.entity.CartItem;
import org.msvdev.ee.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public List<CartItem> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartRepository.save(cartItem);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Long countByProductId(Long productId) {
        return cartRepository.countByProductIdIs(productId);
    }
}