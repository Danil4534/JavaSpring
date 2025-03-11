package com.example.demo.Controllers;

import com.example.demo.Models.Product;
import com.example.demo.Services.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public Flux<Product> getProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/getProduct/{id}")
    public Mono<Product> getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }
    @PostMapping("/createProduct")
    public Mono<Product> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    @PutMapping("/updateProduct/{id}")
    public Mono<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
    @DeleteMapping("/deleteProduct/{id}")
    public Mono<Product> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }
}
