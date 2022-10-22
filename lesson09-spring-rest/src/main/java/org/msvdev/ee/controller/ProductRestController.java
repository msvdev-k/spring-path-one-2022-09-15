package org.msvdev.ee.controller;

import org.msvdev.ee.dto.ProductDto;
import org.msvdev.ee.exception.AppResponse;
import org.msvdev.ee.exception.BadRequestException;
import org.msvdev.ee.exception.NotFoundException;
import org.msvdev.ee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "title") Optional<String> title,
            @RequestParam(name = "min_cost") Optional<BigDecimal> minCost,
            @RequestParam(name = "max_cost") Optional<BigDecimal> maxCost,
            @RequestParam(name = "sort_field") Optional<String> sortField,
            @RequestParam(name = "sort_order", defaultValue = "ASC") String sortOrder) {

        if (page < 1) {
            throw new BadRequestException("The page parameter must be greater than zero");
        }
        if (pageSize < 2) {
            throw new BadRequestException("The page_size parameter must be greater than 1");
        }


        return productService.findWithFilter(page, pageSize,
                title.orElse(null),
                minCost.orElse(null),
                maxCost.orElse(null),
                sortField.orElse(null),
                sortOrder);
    }


    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable(value = "id") Long id) {
        return productService.findById(id).orElseThrow(
                () -> new NotFoundException("Product not found. ID: " + id)
        );
    }


    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto productDto) {

        if (productDto.getId() != null) {
            throw new BadRequestException("Product ID mast be null");
        }

        return productService.save(productDto);
    }


    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {

        if (productDto.getId() == null) {
            throw new BadRequestException("Product ID must not be null");
        }

        return productService.save(productDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse> deleteProductById(@PathVariable(value = "id") Long id) {
        productService.deleteById(id);

        return new ResponseEntity<>(
                new AppResponse(HttpStatus.OK.value(), id.toString()),
                HttpStatus.OK
        );
    }

}