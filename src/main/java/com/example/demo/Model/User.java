package com.example.demo.Model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotBlank()
    @Field("username")
    private String username;

    @NotBlank(message = "Email is required")
    @Field("password")
    private String password;

    @DBRef
    private List<Order> orders;

    public User(){}

    public User(String username, String password, List<Order> orders) {
        this.username = username;
        this.password = password;

    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
