package org.msvdev.ee.controller;

import lombok.RequiredArgsConstructor;
import org.msvdev.ee.dto.ProductDto;
import org.msvdev.ee.entity.Product;
import org.msvdev.ee.entity.User;
import org.msvdev.ee.exception.BadRequestException;
import org.msvdev.ee.exception.NotFoundException;
import org.msvdev.ee.mapper.ProductMapper;
import org.msvdev.ee.service.CartService;
import org.msvdev.ee.service.ProductService;
import org.msvdev.ee.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;

    private final ProductMapper productMapper;



    @GetMapping
    public String indexProductPage(Model model, Principal principal,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize) {

        User user = (principal != null) ? userService.findByUsername(principal.getName())
                    .orElseThrow(() -> new RuntimeException("Не найден пользователь по username: " + principal.getName()))
                    : null;


        if (page < 1) {
            throw new BadRequestException("Параметр page должен быть больше нуля");
        }
        if (pageSize < 2) {
            throw new BadRequestException("Параметр page_size parameter must be greater than 1");
        }

        Page<ProductDto> products = productService.findWithFilter(page, pageSize)
                .map(product -> {
                    ProductDto productDto = productMapper.entityToDto(product);

                    if (user != null && cartService.isProductInUserCart(user.getId(), product.getId())) {
                        productDto.setInCartFlag(Boolean.TRUE);
                    }

                    return productDto;
                });

        model.addAttribute("products", products);

        return "product_list";
    }


    @GetMapping("/{id}")
    public String editProduct(Model model,
            @PathVariable(value = "id") Long id) {

        Product product = productService.findById(id).orElseThrow(
                () -> new NotFoundException("Product not found. ID: " + id)
        );
        ProductDto productDto = productMapper.entityToDto(product);

        model.addAttribute("product", productDto);
        return "product_form";
    }


    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new ProductDto());
        return "product_form";
    }


    @PostMapping("/update")
    public String updateProduct(ProductDto productDto) {
        Product product = productMapper.dtoToEntity(productDto);
        productService.save(product);
        return "redirect:/products";
    }


    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable(value = "id") Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

}