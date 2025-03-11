package com.example.demo.Models;

import lombok.Data;

import java.util.List;
@Data
public class User {
    private String id;
    private String username;
    private String password;
    private List<Order> orders;
}
