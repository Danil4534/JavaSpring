package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class OrderController {
    private  WebClient webClient;
    public OrderController(WebClient.Builder  webClientBuilder) {
        String apiKey = System.getenv("API_KEY");
        this.webClient=webClientBuilder.baseUrl("https://" + apiKey + ".mockapi.io/api/v1/order").build();
    }
    @GetMapping("/orders")
    public Mono<ResponseEntity<JsonNode>> getUsers() {
        return webClient.get()
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/order/getOrderStatus/{id}")
    public Mono<ResponseEntity<String>> getOrderStatus(@PathVariable("id") String id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(response -> {
                    String status = response.path("status").asText();
                    return ResponseEntity.ok(status);
                });
    }

    @GetMapping("/orders/{id}")
    public Mono<ResponseEntity<JsonNode>> getUser(@PathVariable String id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }
    @PostMapping("/orders/createOrder")
    public Mono<ResponseEntity<JsonNode>> createUser(@RequestBody Order order) {
        return webClient.post()
                .bodyValue(order)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/orders/editAddress/{orderId}")
    public Mono<ResponseEntity<String>> editAddress(@PathVariable String orderId, @RequestBody String address) {
        return webClient.put()
                .uri("/{id}",orderId)
                .bodyValue(address)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> ResponseEntity.ok(response));
    }


    @PutMapping("/orders/editOrder/{id}")
    public Mono<ResponseEntity<JsonNode>> editUser(@PathVariable String id, @RequestBody Order order) {
        return  webClient.put()
                .uri("/{id}", id)
                .bodyValue(order)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }
    @DeleteMapping("/orders/deleteOrder/{id}")
    public Mono<ResponseEntity<JsonNode>> deleteUser(@PathVariable String id) {
        return webClient.delete().uri("/{id}", id)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }

}
