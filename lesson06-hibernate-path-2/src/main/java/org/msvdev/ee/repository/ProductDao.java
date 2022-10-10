package org.msvdev.ee.repository;

import org.msvdev.ee.EntityManagerExecutor;
import org.msvdev.ee.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public class ProductDao {

    private final EntityManagerExecutor entityManagerExecutor;


    @Autowired
    public ProductDao(EntityManagerExecutor entityManagerExecutor) {
        this.entityManagerExecutor = entityManagerExecutor;
    }



    public Product findById(Long id) {
        return entityManagerExecutor.executeForEntityManager(
                entityManager -> entityManager.find(Product.class, id)
        );
    }


    public List<Product> findAll() {
        return entityManagerExecutor.executeForEntityManager(
                entityManager -> entityManager.createNamedQuery("findAllProducts", Product.class)
                        .getResultList()
        );
    }


    public void deleteById(Long id) {
        entityManagerExecutor.executeForEntityManagerInTransaction(
                entityManager -> entityManager.createNamedQuery("deleteProductById")
                        .setParameter("id", id)
                        .executeUpdate()
        );
    }


    public Product saveOrUpdate(Product product) {
        entityManagerExecutor.executeForEntityManagerInTransaction(
                entityManager -> entityManager.merge(product)
        );

        return product;
    }

}