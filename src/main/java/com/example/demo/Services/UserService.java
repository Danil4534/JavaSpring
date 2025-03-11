package com.example.demo.Services;

import com.example.demo.Model.User;
import com.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    public List<User> GetAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(String id){
        return userRepository.findById(id).get();
    }

    public  User CreateUser(String username, String password){
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        return userRepository.save(newUser);
    }

    public User updateUser(String id, User user){
        User updatedUser = findUserById(id);
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        return userRepository.save(updatedUser);
    }

    public void deleteUser(String id){
         userRepository.deleteById(id);
    }







}
