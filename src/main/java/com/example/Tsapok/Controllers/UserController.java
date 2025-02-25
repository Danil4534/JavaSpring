package com.example.Tsapok.Controllers;

import com.example.Tsapok.Model.User;
import com.example.Tsapok.Services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;


@Controller
@RequestMapping("/")
public  class UserController {

    @Autowired
    private UserService userService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/AllUsers")
    public List<User> getAllUsers() {
        return userService.GetAllUsers();
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
