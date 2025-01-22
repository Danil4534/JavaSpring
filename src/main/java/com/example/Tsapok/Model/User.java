package com.example.Tsapok.Model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name ="\"User\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false )
    private UUID id;

    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

     @ManyToMany()
     @JoinColumn(name="orders", nullable = true)
     private List<Order> orders;

    public User() {
    }


    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public User(String name, String email, String password, List<Order> orders) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.orders = orders;

    }

}
