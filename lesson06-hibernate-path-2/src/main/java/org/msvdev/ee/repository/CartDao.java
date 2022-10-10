package org.msvdev.ee.repository;

import org.msvdev.ee.EntityManagerExecutor;
import org.msvdev.ee.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CartDao {

    private final EntityManagerExecutor entityManagerExecutor;


    @Autowired
    public CartDao(EntityManagerExecutor entityManagerExecutor) {
        this.entityManagerExecutor = entityManagerExecutor;
    }



    public CartItem findById(Long id) {
        return entityManagerExecutor.executeForEntityManager(
                entityManager -> entityManager.find(CartItem.class, id)
        );
    }


    public List<CartItem> findAllByUserID(Long userID) {
        return entityManagerExecutor.executeForEntityManager(
                entityManager -> entityManager.createNamedQuery("findAllByUserId", CartItem.class)
                        .setParameter("id", userID)
                        .getResultList()
        );
    }


    public List<CartItem> findAllByProductID(Long productID) {
        return entityManagerExecutor.executeForEntityManager(
                entityManager -> entityManager.createNamedQuery("findAllByProductId", CartItem.class)
                        .setParameter("id", productID)
                        .getResultList()
        );
    }


    public void deleteById(Long id) {
        entityManagerExecutor.executeForEntityManagerInTransaction(
                entityManager -> entityManager.createNamedQuery("deleteCartItemById")
                        .setParameter("id", id)
                        .executeUpdate()
        );
    }


    public void deleteByUserId(Long userId) {
        entityManagerExecutor.executeForEntityManagerInTransaction(
                entityManager -> entityManager.createNamedQuery("deleteCartItemByUserId")
                        .setParameter("id", userId)
                        .executeUpdate()
        );
    }


    public void deleteByProductId(Long productId) {
        entityManagerExecutor.executeForEntityManagerInTransaction(
                entityManager -> entityManager.createNamedQuery("deleteCartItemByProductId")
                        .setParameter("id", productId)
                        .executeUpdate()
        );
    }


    public CartItem saveOrUpdate(CartItem cartItem) {
        entityManagerExecutor.executeForEntityManagerInTransaction(
                entityManager -> entityManager.merge(cartItem)
        );

        return cartItem;
    }

}