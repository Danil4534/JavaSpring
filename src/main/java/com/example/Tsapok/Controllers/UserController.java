package com.example.Tsapok.Controllers;

import com.example.Tsapok.Model.User;
import com.example.Tsapok.Services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RequestMapping("/api/users")
@RestController

public  class UserController {

    @Autowired
    private UserService userService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping()
    public List<User> getAllUsers() {
        return userService.GetAllUsers();
    }

    @PostMapping("/register/{name}/{email}/{password}")
    public User registerUser(@PathVariable String name,@PathVariable String email, @PathVariable String password) throws NoSuchAlgorithmException {
        User user = userService.register(name, email, password);
         return user;
    }

    @PostMapping("/login/{email}/{password}")
    public String login(@PathVariable String email, @PathVariable String password ) throws NoSuchAlgorithmException {
        String userLogin = userService.login(email, password);
        return userLogin;
    }
    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User user) {
        userService.updateUser(id, user);
        return user;
    }
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted";
    }

}
