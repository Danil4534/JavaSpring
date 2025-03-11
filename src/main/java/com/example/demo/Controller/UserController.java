package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Services.UserService;
import com.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }


    @PostMapping ("/register/{username}/{password}")
    public ResponseEntity<User> registerUser (@PathVariable String username, @PathVariable String password) {
        User existUser = userRepository.findUserByUsername(username);
        if (existUser == null) {
            User newUser = userService.CreateUser(username, password);
            return ResponseEntity.ok(newUser);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser (@PathVariable String id,@RequestBody User user) {
        userService.updateUser(id, user);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<User> deleteUser (@PathVariable String id) {
        User user = userService.findUserById(id);
        if (user != null) {
            userService.deleteUser(id);
        }
        return ResponseEntity.ok().build();
    }
}
