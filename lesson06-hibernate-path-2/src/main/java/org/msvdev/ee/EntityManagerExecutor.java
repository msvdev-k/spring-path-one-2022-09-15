package org.msvdev.ee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;


/**
 * Вспомогательный класс для работы с EntityManager
 */
@Component
public class EntityManagerExecutor {

    private final EntityManagerFactory entityManagerFactory;


    @Autowired
    public EntityManagerExecutor(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public  <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return function.apply(entityManager);

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }


    public void executeForEntityManagerInTransaction(Consumer<EntityManager> consumer) {
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