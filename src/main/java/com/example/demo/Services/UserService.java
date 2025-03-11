package com.example.demo.Services;

import com.example.demo.Models.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final WebClient webClient;

    public UserService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<User> getUser(String id) {
        return webClient.get()
                .uri("/getUser/{id}",id)
                .retrieve()
                .bodyToMono(User.class);
    }



    public Flux<User> getUsers() {
        return webClient.get().uri("/users")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(User.class);
    }

    public Mono<User> registerUser(String username, String password) {
        return webClient.post().uri("/register/{username}/{password}", username,password)
                .retrieve()
                .bodyToMono(User.class);
    }

    public Mono<User> updateUser(String id, User user) {
        return webClient.put()
                .uri("/updateUser/{id}", id)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class);
    }

    public Mono<User> deleteUser(String id) {
        return webClient.delete()
                .uri("/deleteUser/{id}", id)
                .retrieve()
                .bodyToMono(User.class);
    }
}
