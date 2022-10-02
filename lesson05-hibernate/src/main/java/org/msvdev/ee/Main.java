package org.msvdev.ee;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import org.msvdev.ee.entity.Product;
import org.msvdev.ee.repository.ProductDao;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();


        ProductDao productDao = new ProductDao(entityManagerFactory);


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
        product_update.setPrice(150000);
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
}