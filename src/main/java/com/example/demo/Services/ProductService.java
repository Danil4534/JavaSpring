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
    public Product findById(String id) {
        return productRepository.findById(id).get();
    }
    public Product CreateProduct(Product product) {
        return productRepository.save(product);
    }
    public Product UpdateProduct(String id,Product product) {
        Product oldProduct = productRepository.findById(id).get();
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setDescription(product.getDescription());
        return productRepository.save(oldProduct);
    }
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }


}
