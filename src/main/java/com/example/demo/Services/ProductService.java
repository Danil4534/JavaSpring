package com.example.demo.Services;

import com.example.demo.Models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    private WebClient webClient;
    public ProductService(WebClient webClient) {
        this.webClient = webClient;
    }
    public Flux<Product> getAllProducts() {
        return webClient.get()
                .uri("/getProducts")
                .retrieve()
                .bodyToFlux(Product.class);
    }
    public Mono<Product> getProduct(String id) {
        return webClient.get()
                .uri("/findProduct/{id}",  id)
                .retrieve().bodyToMono(Product.class);
    }

    public Mono<Product> createProduct(Product product) {
        return webClient.post()
                .uri("/createProduct")
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class);
    }
    public Mono<Product> updateProduct(String id, Product product) {
        return webClient.put()
                .uri("/updateProduct/{id}", id)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class);
    }

    public Mono<Product> deleteProduct(String id) {
        return  webClient.delete()
                .uri("/deleteProduct/{id}", id)
                .retrieve()
                .bodyToMono(Product.class);
    }
}
