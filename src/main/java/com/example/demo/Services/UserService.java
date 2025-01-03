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
    public User getUserById(Long id) {
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
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
