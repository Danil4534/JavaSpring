package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping
public class OrderController {
    private  WebClient webClient;
    public OrderController(WebClient.Builder  webClientBuilder) {
        String apiKey = System.getenv("API_KEY");
        this.webClient=webClientBuilder.baseUrl("https://" + apiKey + ".mockapi.io/api/v1/order").build();
    }

    @GetMapping("/order")
    public Mono<ResponseEntity<JsonNode>> getOrders() {
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

    @GetMapping("/order/{id}")
    public Mono<ResponseEntity<JsonNode>> getUser(@PathVariable String id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }
    @PostMapping("/order/createOrder")
    public Mono<ResponseEntity<JsonNode>> createUser(@RequestBody Order order) {
        return webClient.post()
                .bodyValue(order)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/order/editAddress/{orderId}")
    public Mono<ResponseEntity<String>> editAddress(@PathVariable String orderId, @RequestBody String address) {
        return webClient.put()
                .uri("/{id}",orderId)
                .bodyValue(address)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> ResponseEntity.ok(response));
    }


    @PutMapping("/order/editOrder/{id}")
    public Mono<ResponseEntity<JsonNode>> editUser(@PathVariable String id, @RequestBody Order order) {
        return  webClient.put()
                .uri("/{id}", id)
                .bodyValue(order)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }
    @PutMapping("/order/deleteOrder/{id}")
    public Mono<ResponseEntity<JsonNode>> deleleOrder(@PathVariable String id,@RequestBody Map<String, String> status) {
        return webClient.put()
                .uri("/{id}", id)
                .bodyValue(status)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }

}
