package com.cyyaw.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable String id) {
        return productService.findById(id);
    }

    @GetMapping
    public Iterable<Product> getAllProducts() {
        return productService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable String id) {
        productService.deleteById(id);
    }
}
