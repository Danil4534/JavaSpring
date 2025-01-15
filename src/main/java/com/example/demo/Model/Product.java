package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(name ="\"Product\"")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_id_gen")
    private Long id;

    @Column( name = "name")
    private String name;

    @Column(name="description")
    private String description;

    @Column( name = "price")
    private int price;

    @Column(name="count")
    private int count;

    public Product() {}

    public Product(String name, String description, int price, int count) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getPrice() {
        return price;
    }
   public void setPrice(int price) {
        this.price = price;
   }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }



}
