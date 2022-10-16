package org.msvdev.ee.controller;

import org.msvdev.ee.entity.Product;
import org.msvdev.ee.exception.NotFoundException;
import org.msvdev.ee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductService productService;


    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<Product> getAllProducts(@RequestParam(name = "costMin") Optional<BigDecimal> costMin,
                                        @RequestParam(name = "costMax") Optional<BigDecimal> costMax) {
        return productService.findAllWithCostFilter(costMin, costMax);
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(value = "id") Long id) {
        return productService.findById(id).orElseThrow(
                () -> new NotFoundException("Product not found. Id: " + id)
        );
    }


    @PostMapping
    public void addProduct(Product product) {
        productService.save(product);
    }


    @GetMapping("/delete/{id}")
    public void deleteProductById(@PathVariable(value = "id") Long id) {
        productService.deleteById(id);
    }

}