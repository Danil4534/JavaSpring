package com.example.Tsapok.Services;

import com.example.Tsapok.Model.User;
import com.example.Tsapok.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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
    public User register(String name, String email, String password) throws NoSuchAlgorithmException {
       User user = new User();
       user.setName(name);
       user.setEmail(email);
       user.setPassword(hashPassword(password));
        return userRepository.save(user);
    }
    public String login(String email, String password) throws NoSuchAlgorithmException {
        User user = userRepository.findByEmail(email);
        if(user.getPassword().equals(hashPassword(password))){
            String token = generateToken(user);
            return token;
        }
        return null;
    }

    public String generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .compact();
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedPassword = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hashedPassword){
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) sb.append('0');
            sb.append(hex);
        }
        return  sb.toString();
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
