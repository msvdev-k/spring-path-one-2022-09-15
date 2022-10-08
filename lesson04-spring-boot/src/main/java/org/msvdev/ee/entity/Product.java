package org.msvdev.ee.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class Product {

    private Long id;

    @NotEmpty(message = "Необходимо указать название продукта")
    private String title;

    @NotNull(message = "Необходимо указать цену")
    private BigDecimal cost;


    public Product() {
    }

    public Product(String title, BigDecimal cost) {
        this.title = title;
        this.cost = cost;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}