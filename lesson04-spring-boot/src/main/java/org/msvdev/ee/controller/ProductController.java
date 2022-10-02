package org.msvdev.ee.controller;

import org.msvdev.ee.entity.Product;
import org.msvdev.ee.exception.NotFoundException;
import org.msvdev.ee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public String indexProductPage(Model model) {

        model.addAttribute("products", productService.findAll());
        return "product_list";
    }


    @GetMapping("/{id}")
    public String editProduct(@PathVariable(value = "id") Long id, Model model) {

        model.addAttribute("product", productService.findById(id)
                .orElseThrow(NotFoundException::new));

        return "product_form";
    }


    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute(new Product());
        return "product_form";
    }


    @PostMapping("/update")
    public String updateProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product";
    }


    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {

        productService.deleteById(id);
        return "redirect:/product";
    }


    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}