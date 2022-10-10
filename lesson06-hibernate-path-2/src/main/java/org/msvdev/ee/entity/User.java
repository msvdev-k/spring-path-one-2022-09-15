package org.msvdev.ee.entity;

import jakarta.persistence.*;

import java.util.List;


/**
 * Покупатель
 */
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "findAllUsers", query = "FROM User"),
        @NamedQuery(name = "deleteUserById", query = "DELETE FROM User u WHERE u.id=:id")
})
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<CartItem> cartItems;


    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}