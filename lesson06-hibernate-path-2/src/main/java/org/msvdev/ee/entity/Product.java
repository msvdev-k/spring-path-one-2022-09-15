package org.msvdev.ee.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;


/**
 * Товар
 */
@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "FROM Product"),
        @NamedQuery(name = "deleteProductById", query = "DELETE FROM Product p WHERE p.id=:id")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private BigDecimal price;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<CartItem> cartItems;


    public Product() {
    }


    public Product(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public Product(String title, int price) {
        this.title = title;
        this.price = new BigDecimal(price);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}