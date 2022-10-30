package org.msvdev.ee.mapper;

import org.msvdev.ee.dto.CartItemDto;
import org.msvdev.ee.entity.CartItem;
import org.springframework.stereotype.Component;


@Component
public class CartItemMapper {

    public CartItem dtoToEntity(CartItemDto cartItemDto) {
        return new CartItem(cartItemDto.getId(), cartItemDto.getProductId(), cartItemDto.getReservedCost(), cartItemDto.getQuantity());
    }

    public CartItemDto entityToDto(CartItem cartItem) {
        return new CartItemDto(cartItem.getId(), cartItem.getProductId(), cartItem.getReservedCost(), cartItem.getQuantity());
    }

}