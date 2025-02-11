package com.example.demo;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private String id;
    private String name;
    private String lastname;
    private Date createdAt;
}
