package com.example.demo.Controllers;

import com.example.demo.Models.User;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public Flux<User> getUsers() {
        return userService.getUsers();
    }
    @GetMapping("/users/{id}")
    public Mono<User> getUser(@PathVariable("id") String id) {
        return userService.getUser(id);
    }
    @PostMapping("/users/createUser")
    public Mono<User> registerUser(@RequestBody User user) {
        return userService.registerUser(user.getUsername(), user.getPassword());
    }
    @PutMapping("/updateUser/{id}")
    public Mono<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        return  userService.updateUser(id, user);
    }
    @DeleteMapping("/deleteUser/{id}")
    public Mono<User> deleteUser(@PathVariable("id") String id) {
        return userService.deleteUser(id);
    }



}
