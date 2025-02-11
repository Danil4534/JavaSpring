package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping()
public class UserController {

    private  final WebClient webClient;

    public UserController(WebClient.Builder  webClientBuilder) {
        String apiKey = System.getenv("API_KEY");
        this.webClient=webClientBuilder.baseUrl("https://" + apiKey + ".mockapi.io/api/v1/User").build();
    }

    @GetMapping("/users")
    public Mono<ResponseEntity<JsonNode>> getUsers() {
        return webClient.get()
                .retrieve()
                .bodyToMono(JsonNode.class) // ✅ Повертає JSON-об'єкт
                .map(ResponseEntity::ok);
    }

    @GetMapping("/users/{id}")
    public Mono<ResponseEntity<JsonNode>> getUser(@PathVariable String id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }
    @PostMapping("/users/createUser")
    public Mono<ResponseEntity<JsonNode>> createUser(@RequestBody User user) {
        return webClient.post()
                .bodyValue(user)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/users/editUser/{id}")
    public Mono<ResponseEntity<JsonNode>> editUser(@PathVariable String id, @RequestBody User user) {
        return  webClient.put()
                .uri("/{id}", id)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/users/deleteUser/{id}")
    public Mono<ResponseEntity<JsonNode>> deleteUser(@PathVariable String id) {
        return webClient.delete().uri("/{id}", id)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }




}
