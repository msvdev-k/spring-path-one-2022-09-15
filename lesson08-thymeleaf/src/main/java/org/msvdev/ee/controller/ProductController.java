package org.msvdev.ee.controller;

import org.msvdev.ee.entity.Product;
import org.msvdev.ee.exception.NotFoundException;
import org.msvdev.ee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public String getAllProducts(Model model,
                                 @RequestParam(name = "page") Optional<Integer> page,
                                 @RequestParam(name = "pageSize") Optional<Integer> pageSize) {

        model.addAttribute("products",
                productService.findAllWithPageFilter(page, pageSize)
        );

        return "product_list";
    }


    @GetMapping("/{id}")
    public String editProduct(Model model,
                              @PathVariable(value = "id") Long id) {

        model.addAttribute("product", productService.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found. Id: " + id)));
        return "product_form";
    }


    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute(new Product());
        return "product_form";
    }


    @PostMapping("/update")
    public String updateProduct(Product product) {
        productService.save(product);
        return "redirect:/products";
    }


    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable(value = "id") Long id) {

        productService.deleteById(id);
        return "redirect:/products";
    }

}