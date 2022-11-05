package org.msvdev.ee.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.msvdev.ee.entity.Product;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private Product product;
    private Integer quantity;

}