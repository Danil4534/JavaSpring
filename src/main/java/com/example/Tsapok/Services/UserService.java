package com.example.Tsapok.Services;

import com.example.Tsapok.Model.User;
import com.example.Tsapok.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> GetAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(UUID id) {
        User user = userRepository.findById(id).get();
        return user;
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(User user) {
        User updatedUser = getUserById(user.getId());
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        return userRepository.save(updatedUser);
    }
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
