package com.example.demo.Models;

import lombok.Data;

@Data
public class Product {
    private String id;
    private String name;
    private String description;
    private int price;
    private int count;
}
