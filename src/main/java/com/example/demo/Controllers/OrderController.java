package com.example.demo.Controllers;

import com.example.demo.Models.Order;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class OrderController {
    private WebClient webClient;

    public OrderController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/orders")
    public Mono<ResponseEntity<String>> getOrders() {
        return webClient.get()
                .uri("/order")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(response -> ResponseEntity.ok().body(response.toString()));
    }

    @GetMapping("/order/getOrderStatus/{id}")
    public Mono<ResponseEntity<String>> getOrderStatus(@PathVariable String id) {
        return webClient.get()
                .uri("/order/getOrderStatus/{id}", id)
                .accept(MediaType.APPLICATION_JSON) // Указываем, что ожидаем JSON
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> ResponseEntity.ok(response));
    }

    @GetMapping("/order/{id}")
    public Mono<ResponseEntity<String>> getOrderById(@PathVariable("id") String id) {
        return webClient.get()
                .uri("/order/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok);
    }

    @PostMapping(value = "/order/createOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<JsonNode>> createOrder(@RequestBody Order order) {
        return webClient.post()
                .uri("/order/createOrder")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(order)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }
}