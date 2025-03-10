package com.example.demo;

import com.example.demo.Model.Order;
import com.example.demo.Model.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(@NotBlank() String username);
}
