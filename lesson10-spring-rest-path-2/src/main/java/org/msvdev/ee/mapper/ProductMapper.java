package org.msvdev.ee.mapper;

import org.msvdev.ee.dto.ProductDto;
import org.msvdev.ee.entity.Product;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {

    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getCost(), Boolean.FALSE);
    }

}