package com.example.demo;

import lombok.Data;

import java.util.Date;
@Data
public class Order {
   private String id;
   private String userId;
   private String product;
   private String price;
   private String address;
   private String status;
   private Date createdAt;

}
