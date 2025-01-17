package com.example.Tsapok.Services;

import com.example.Tsapok.Model.Product;
import com.example.Tsapok.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(UUID id,Product product) {
        Product oldProduct = this.findById(id);
        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setCount(product.getCount());
        return productRepository.save(oldProduct);
    }
    public Product deleteProduct(UUID id) {
        Product oldProduct = this.findById(id);
        productRepository.delete(oldProduct);
        return oldProduct;
    }
}
