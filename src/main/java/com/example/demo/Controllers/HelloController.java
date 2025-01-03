package com.example.demo.Controllers;

import com.example.demo.Model.User;
import com.example.demo.Services.UserService;
import com.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public  class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.GetAllUsers();
    }

}
