package org.msvdev.ee.service;

import org.msvdev.ee.entity.CartItem;
import org.msvdev.ee.entity.Product;
import org.msvdev.ee.entity.User;
import org.msvdev.ee.repository.ProductDao;
import org.msvdev.ee.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Сервис корзины
 */
@Service
public class CartService {

    private final UserDao userDao;
    private final ProductDao productDao;


    @Autowired
    public CartService(UserDao userDao, ProductDao productDao) {
        this.userDao = userDao;
        this.productDao = productDao;
    }

//    Создаете сервис, позволяющий по id покупателя узнать список купленных им товаров,
//    и по id товара узнавать список покупателей этого товара;


    public List<Product> productListByUserId(Long userId) {

        User user = userDao.findById(userId);

        if (user == null) {
            return null;
        }

        return user.getCartItems()
                .stream()
                .map(CartItem::getProduct)
                .toList();
    }



    public List<User> userListByProductId(Long productId) {

        Product product = productDao.findById(productId);

        if (product == null) {
            return null;
        }

        return product.getCartItems()
                .stream()
                .map(CartItem::getUser)
                .toList();
    }

}