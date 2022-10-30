package org.msvdev.ee.controller;

import lombok.RequiredArgsConstructor;
import org.msvdev.ee.dto.CartItemDto;
import org.msvdev.ee.entity.CartItem;
import org.msvdev.ee.exception.AppResponse;
import org.msvdev.ee.exception.BadRequestException;
import org.msvdev.ee.exception.NotFoundException;
import org.msvdev.ee.mapper.CartItemMapper;
import org.msvdev.ee.mapper.ProductMapper;
import org.msvdev.ee.service.CartService;
import org.msvdev.ee.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartRestController {

    private final ProductService productService;
    private final CartService cartService;

    private final ProductMapper productMapper;
    private final CartItemMapper cartItemMapper;



    @GetMapping
    public List<CartItemDto> getAllItems() {
        List<CartItem> cartItems = cartService.findAll();
        return cartItems.stream().map(cartItemMapper::entityToDto).toList();
    }


    @GetMapping("/{id}")
    public CartItemDto getItemById(@PathVariable(value = "id") Long id) {
        CartItem cartItem = cartService.findById(id).orElseThrow(
                () -> new NotFoundException("Product in cart with id " + id + " not found.")
        );

        return cartItemMapper.entityToDto(cartItem);
    }


    @PostMapping
    public CartItemDto addItem(@RequestBody CartItemDto cartItemDto) {

        if (cartItemDto.getId() != null) {
            throw new BadRequestException("CartItem ID mast be null");
        }

        CartItem cartItem = cartItemMapper.dtoToEntity(cartItemDto);
        cartItem = cartService.save(cartItem);

        return cartItemMapper.entityToDto(cartItem);
    }


    @PutMapping
    public CartItemDto updateItem(@RequestBody CartItemDto cartItemDto) {

        if (cartItemDto.getId() == null) {
            throw new BadRequestException("CartItem ID must not be null");
        }

        CartItem cartItem = cartItemMapper.dtoToEntity(cartItemDto);
        cartItem = cartService.save(cartItem);

        return cartItemMapper.entityToDto(cartItem);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse> deleteItemById(@PathVariable(value = "id") Long id) {
        cartService.deleteById(id);

        return new ResponseEntity<>(
                new AppResponse(HttpStatus.OK.value(), id.toString()),
                HttpStatus.OK
        );
    }

}