package org.msvdev.ee.dto;

import org.msvdev.ee.entity.Product;

import java.math.BigDecimal;


public class ProductDto {

    private Long id;
    private String title;
    private BigDecimal cost;


    public ProductDto() {
    }

    public ProductDto(String title, BigDecimal cost) {
        this.title = title;
        this.cost = cost;
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
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