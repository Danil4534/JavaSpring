package com.example.demo.Controller;

import com.example.demo.Model.Product;
import com.example.demo.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products= productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/findProduct/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable String id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }
    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

           Product newProduct = productService.createProduct(product);
           return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);

    }
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        Product existingProduct = productService.findById(id);
        if(existingProduct !=null){
            productService.UpdateProduct(id, product);
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
        Product product = productService.findById(id);
        if(product!=null){
            productService.deleteProduct(id);
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

}
