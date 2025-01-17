package com.example.Tsapok.Controllers;

import com.example.Tsapok.Model.User;
import com.example.Tsapok.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public  class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.GetAllUsers();
    }

}
