package org.msvdev.ee;

import org.msvdev.ee.entity.CartItem;
import org.msvdev.ee.entity.Product;
import org.msvdev.ee.entity.User;
import org.msvdev.ee.repository.CartDao;
import org.msvdev.ee.repository.ProductDao;
import org.msvdev.ee.repository.UserDao;
import org.msvdev.ee.service.CartService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        productDaoExample(context);
        userDaoExample(context);
        cartDaoExample(context);

    }


    private static void productDaoExample(ApplicationContext context) {

        System.out.println("\n===============================");
        System.out.println("= productDaoExample");
        System.out.println("===============================\n");


        ProductDao productDao = context.getBean(ProductDao.class);


        //////////////////////////////////////////////////////////
        // INSERT
        //////////////////////////////////////////////////////////
        Product product1 = new Product("Телевизор", 10000);
        productDao.saveOrUpdate(product1);

        Product product2 = new Product("Микроволновая печь", 6800);
        productDao.saveOrUpdate(product2);

        Product product3 = new Product("Холодильник", 68000);
        productDao.saveOrUpdate(product3);

        Product product4 = new Product("Тостер", 3800);
        productDao.saveOrUpdate(product4);


        //////////////////////////////////////////////////////////
        // SELECT
        //////////////////////////////////////////////////////////

        System.out.println("\n===============================");
        System.out.println("All products");

        List<Product> productList = productDao.findAll();
        System.out.println(productList);


        System.out.println("\n===============================");
        System.out.println("Product with id 2");

        Product product = productDao.findById(2L);
        System.out.println(product);


        //////////////////////////////////////////////////////////
        // UPDATE
        //////////////////////////////////////////////////////////

        System.out.println("\n===============================");
        System.out.println("Update Product with id 1");
        Product product_update = productDao.findById(1L);
        product_update.setPrice(new BigDecimal(150000));
        productDao.saveOrUpdate(product_update);

        productList = productDao.findAll();
        System.out.println(productList);



        //////////////////////////////////////////////////////////
        // DELETE
        //////////////////////////////////////////////////////////

        System.out.println("\n===============================");
        System.out.println("Delete product with id 2");

        productDao.deleteById(2L);

        productList = productDao.findAll();
        System.out.println(productList);

    }


    private static void userDaoExample(ApplicationContext context) {

        System.out.println("\n===============================");
        System.out.println("= userDaoExample");
        System.out.println("===============================\n");


        UserDao userDao = context.getBean(UserDao.class);



        //////////////////////////////////////////////////////////
        // INSERT
        //////////////////////////////////////////////////////////
        User user1 = new User("Тимофей");
        userDao.saveOrUpdate(user1);

        User user2 = new User("Михаил");
        userDao.saveOrUpdate(user2);

        User user3 = new User("Игорь");
        userDao.saveOrUpdate(user3);

        User user4 = new User("Светлана");
        userDao.saveOrUpdate(user4);


        //////////////////////////////////////////////////////////
        // SELECT
        //////////////////////////////////////////////////////////

        System.out.println("\n===============================");
        System.out.println("All users");

        List<User> userList = userDao.findAll();
        System.out.println(userList);


        System.out.println("\n===============================");
        System.out.println("User with id 2");

        User user = userDao.findById(2L);
        System.out.println(user);


        //////////////////////////////////////////////////////////
        // UPDATE
        //////////////////////////////////////////////////////////

        System.out.println("\n===============================");
        System.out.println("Update User with id 1");
        User user_update = userDao.findById(1L);
        user_update.setUsername("Николай");
        userDao.saveOrUpdate(user_update);

        userList = userDao.findAll();
        System.out.println(userList);



        //////////////////////////////////////////////////////////
        // DELETE
        //////////////////////////////////////////////////////////

        System.out.println("\n===============================");
        System.out.println("Delete user with id 2");

        userDao.deleteById(2L);

        userList = userDao.findAll();
        System.out.println(userList);

    }




    private static void cartDaoExample(ApplicationContext context) {

        System.out.println("\n===============================");
        System.out.println("= cartDaoExample");
        System.out.println("===============================\n");


        UserDao userDao = context.getBean(UserDao.class);
        ProductDao productDao = context.getBean(ProductDao.class);
        CartDao cartDao = context.getBean(CartDao.class);
        CartService cartService = context.getBean(CartService.class);



        List<User> userList = userDao.findAll();
        List<Product> productList = productDao.findAll();


        //////////////////////////////////////////////////////////
        // INSERT
        //////////////////////////////////////////////////////////
        CartItem cartItem1 = new CartItem(userList.get(0), productList.get(0), 2);
        cartDao.saveOrUpdate(cartItem1);

        CartItem cartItem2 = new CartItem(userList.get(0), productList.get(2), 2);
        cartDao.saveOrUpdate(cartItem2);

        CartItem cartItem3 = new CartItem(userList.get(2), productList.get(0), 2);
        cartDao.saveOrUpdate(cartItem3);

        CartItem cartItem4 = new CartItem(userList.get(2), productList.get(2), 2);
        cartDao.saveOrUpdate(cartItem4);



        //////////////////////////////////////////////////////////
        // CartService
        //////////////////////////////////////////////////////////

        System.out.println("\n===============================");
        System.out.println("Find products by user id " + userList.get(2).getId());

        List<Product> products = cartService.productListByUserId(userList.get(2).getId());
        System.out.println(products);

        System.out.println("\n===============================");
        System.out.println("Find users by product id " + productList.get(0).getId());

        List<User> users = cartService.userListByProductId(productList.get(0).getId());
        System.out.println(users);



        //////////////////////////////////////////////////////////
        // SELECT
        //////////////////////////////////////////////////////////

        System.out.println("\n===============================");
        System.out.println("Find CartItem with id 2");

        CartItem cartItem = cartDao.findById(2L);
        System.out.println(cartItem);


        System.out.println("\n===============================");
        System.out.println("Find CartItem with user id " + userList.get(2).getId());

        List<CartItem> cartItems1 = cartDao.findAllByUserID(userList.get(2).getId());
        System.out.println(cartItems1);


        System.out.println("\n===============================");
        System.out.println("Find CartItem with product id " + productList.get(0).getId());

        List<CartItem> cartItems2 = cartDao.findAllByProductID(productList.get(0).getId());
        System.out.println(cartItems2);


        //////////////////////////////////////////////////////////
        // UPDATE
        //////////////////////////////////////////////////////////

        System.out.println("\n===============================");
        System.out.println("Update CartItem with id 1");

        CartItem cartItemUpdate = cartDao.findById(1L);
        cartItemUpdate.setQuantity(10);
        cartItemUpdate = cartDao.saveOrUpdate(cartItemUpdate);

        System.out.println(cartItemUpdate);



        //////////////////////////////////////////////////////////
        // DELETE
        //////////////////////////////////////////////////////////

        System.out.println("\n===============================");
        System.out.println("Delete CartItem");

        cartDao.deleteById(2L);

        System.out.println("Delete CartItem with user id " + userList.get(0).getId());
        cartDao.deleteByUserId(userList.get(0).getId());

        System.out.println("Delete CartItem with product id " + productList.get(2).getId());
        cartDao.deleteByProductId(productList.get(2).getId());

    }

}