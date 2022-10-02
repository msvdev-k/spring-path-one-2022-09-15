package org.msvdev.ee.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.msvdev.ee.entity.Product;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


public class ProductDao {

    private final EntityManagerFactory entityManagerFactory;


    public ProductDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


//    Создайте класс ProductDao и реализуйте в нем логику выполнения CRUD-операций над сущностью Product
//    (Product findById(Long id), List<Product> findAll(), void deleteById(Long id), Product saveOrUpdate(Product product))


    public Product findById(Long id) {
        return executeForEntityManager(
                entityManager -> entityManager.find(Product.class, id)
        );
    }


    public List<Product> findAll() {
        return executeForEntityManager(
                entityManager -> entityManager.createNamedQuery("findAllProducts", Product.class)
                        .getResultList()
        );
    }


    public void deleteById(Long id) {
        executeForEntityManagerInTransaction(
                entityManager -> entityManager.createNamedQuery("deleteProductById")
                        .setParameter("id", id)
                        .executeUpdate()
        );
    }


    public Product saveOrUpdate(Product product) {
        executeForEntityManagerInTransaction(
                entityManager -> entityManager.merge(product)
        );

        return product;
    }


    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return function.apply(entityManager);

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }


    private void executeForEntityManagerInTransaction(Consumer<EntityManager> consumer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            consumer.accept(entityManager);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

}