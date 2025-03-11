package com.example.demo.Services;

import com.example.demo.Models.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {
    private final WebClient webClient;
    public OrderService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Order> getOrders() {
        return webClient.get()
                .uri("/orders")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(Order.class);

    }

    public Mono<Order> getOrder(String id) {
        return webClient.get()
                .uri("/findOrder/{id}", id)
                .retrieve()
                .bodyToMono(Order.class);

    }

    public Mono<Order> createOrder(String userId,String address, String productId) {
        return webClient.post()
                .uri("/createOrder/{userId}/{address}/{productId}", userId, address, productId)
                .retrieve()
                .bodyToMono(Order.class);
    }

    public Mono<String> getOrderStatus(String orderId) {
        return  webClient.get()
                .uri("/order/getOrderStatus/{id}", orderId)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<Order> updateOrderAddress(String id,String address) {
        return  webClient.put()
                .uri("/updateOrderAddress/{id}/{address}",id,address)
                .retrieve()
                .bodyToMono(Order.class);
    }

    public Mono<Order> deleteOrder(String id) {
        return webClient.delete()
                .uri("/deleteOrder/{id}", id)
                .retrieve()
                .bodyToMono(Order.class);
    }

}
