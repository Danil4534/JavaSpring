package com.example.demo.Models;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private String id;
    private User user;
    private Product product;
    private String status;
    private String address;
    private Date createDate;
}
