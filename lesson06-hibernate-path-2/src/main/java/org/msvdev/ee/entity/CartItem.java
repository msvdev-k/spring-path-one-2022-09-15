package org.msvdev.ee.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;


/**
 * Товар в корзине покупателя
 */
@Entity
@Table(name = "cart_items")
@NamedQueries({
        @NamedQuery(name = "findAllByUserId", query = "FROM CartItem ci WHERE ci.user.id=:id"),
        @NamedQuery(name = "findAllByProductId", query = "FROM CartItem ci WHERE ci.product.id=:id"),
        @NamedQuery(name = "deleteCartItemById", query = "DELETE FROM CartItem ci WHERE ci.id=:id"),
        @NamedQuery(name = "deleteCartItemByUserId", query = "DELETE FROM CartItem ci WHERE ci.user.id=:id"),
        @NamedQuery(name = "deleteCartItemByProductId", query = "DELETE FROM CartItem ci WHERE ci.product.id=:id")
})
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private BigDecimal price;

    @Column
    private int quantity;



    public CartItem() {
    }


    public CartItem(User user, Product product, int quantity) {
        this.user = user;
        this.product = product;
        this.price = product.getPrice();
        this.quantity = quantity;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}