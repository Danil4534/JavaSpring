package com.example.Tsapok.Services;

import com.example.Tsapok.Enum.Role;
import com.example.Tsapok.Model.User;
import com.example.Tsapok.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> GetAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        User user = userRepository.findById(id).get();
        return user;
    }
    public User register(String username, String password, List<Role> roles){
            User user= userRepository.findByUsername(username);
            if (user != null) {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            User newUser = createUser( username, password, roles);
            return userRepository.save(newUser);
    }

    public User createUser ( String username, String password, List<Role> roles){
        User user = new User();

        user.setUsername(username);
        user.setPassword(hashPassword(password));
        user.setRoles(roles);
        return userRepository.save(user);
    }
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if(user.getUsername().equals(hashPassword(password))){
            String token = generateToken(user);
            return token;
        }
        return null;
    }


    public String generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("password", user.getPassword());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .compact();
    }

    public String hashPassword(String password){
        String hash =  passwordEncoder.encode(password);
        return  hash;
    }
    public User updateUser(Long id,User user) {
        User updatedUser = getUserById(id);
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        return userRepository.save(updatedUser);
    }

    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
