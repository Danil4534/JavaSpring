package com.example.demo.Services;

import com.example.demo.Model.Product;
import com.example.demo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Product product = productRepository.findById(id).get();
        return product;
    }
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id,Product product) {
        Product oldProduct = this.findById(id);
        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setCount(product.getCount());
        return productRepository.save(oldProduct);
    }
    public Product deleteProduct(Long id) {
        Product oldProduct = this.findById(id);
        productRepository.delete(oldProduct);
        return oldProduct;
    }
}
