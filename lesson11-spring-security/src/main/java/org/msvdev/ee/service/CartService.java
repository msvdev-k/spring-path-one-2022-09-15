package org.msvdev.ee.service;

import lombok.RequiredArgsConstructor;
import org.msvdev.ee.entity.Product;
import org.msvdev.ee.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
@RequiredArgsConstructor
public class CartService {

    private final Map<Long, Map<Long, CartItem>> lineItemsMap = new ConcurrentHashMap<>();

    private final ProductService productService;



    public Map<Long, CartItem> findAllItemsForUser(Long userId) {
        return new HashMap<>(lineItemsMap.get(userId));
    }


    public boolean isProductInUserCart(Long userId, Long productId) {
        return lineItemsMap.computeIfAbsent(userId, k -> new HashMap<>()).containsKey(productId);
    }


    public void addProductForUserQty(Long userId, Long productId, Integer qty) {
        Map<Long, CartItem> itemsForUser = lineItemsMap.computeIfAbsent(userId, k -> new HashMap<>());

        if (itemsForUser.containsKey(productId)) {

            CartItem item = itemsForUser.get(productId);
            item.setQuantity(item.getQuantity() + qty);
            itemsForUser.put(productId, item);

        } else {

            Product product = productService.findById(productId)
                    .orElseThrow(() -> new NotFoundException("Product not found. ID: " + productId));

            CartItem item = new CartItem(product, qty);
            itemsForUser.put(productId, item);
        }
    }


    public void removeProductForUser(Long userId, Long productId) {
        Map<Long, CartItem> itemsForUser = lineItemsMap.get(userId);

        if (itemsForUser == null) {
            return;
        }

        itemsForUser.remove(productId);
    }


    public void removeProductForUser(Long userId, Long productId, Integer qty) {
        Map<Long, CartItem> itemsForUser = lineItemsMap.get(userId);

        if (itemsForUser == null) {
            return;
        }

        CartItem item = itemsForUser.get(productId);
        if (item != null) {
            if (item.getQuantity() <= qty) {
                itemsForUser.remove(productId);
            } else {
                item.setQuantity(item.getQuantity() + qty);
                itemsForUser.put(productId, item);
            }
        }
    }


    public void removeAllForUser(Long userId) {
        lineItemsMap.remove(userId);
    }

}