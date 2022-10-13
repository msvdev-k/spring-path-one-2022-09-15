package org.msvdev.ee.repository;

import org.msvdev.ee.EntityManagerExecutor;
import org.msvdev.ee.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDao {

    private final EntityManagerExecutor entityManagerExecutor;

    @Autowired
    public UserDao(EntityManagerExecutor entityManagerExecutor) {
        this.entityManagerExecutor = entityManagerExecutor;
    }



    public User findById(Long id) {
        return entityManagerExecutor.executeForEntityManager(
                entityManager -> entityManager.find(User.class, id)
        );
    }


    public List<User> findAll() {
        return entityManagerExecutor.executeForEntityManager(
                entityManager -> entityManager.createNamedQuery("findAllUsers", User.class)
                        .getResultList()
        );
    }


    public void deleteById(Long id) {
        entityManagerExecutor.executeForEntityManagerInTransaction(
                entityManager -> entityManager.createNamedQuery("deleteUserById")
                        .setParameter("id", id)
                        .executeUpdate()
        );
    }


    public User saveOrUpdate(User user) {
        entityManagerExecutor.executeForEntityManagerInTransaction(
                entityManager -> entityManager.merge(user)
        );

        return user;
    }

}