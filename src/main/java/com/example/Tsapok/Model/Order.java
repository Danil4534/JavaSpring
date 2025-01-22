package com.example.Tsapok.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "\"Orders\"")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @OneToMany
    private List<Product> product;

    @Column(name="status")
    private String status;

    @Column(name = "CreateDate")
    private Date CreateDate;

    public Order() {}

    public Order(User userId, List<Product> product, String status) {
        this.userId = userId;
        this.product = product;
        this.status = status;
        this.CreateDate = new Date();
    }


    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public User getUserId() {
        return userId;
    }
    public void setUserId(User userId) {
        this.userId = userId;
    }



    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getCreateDate() {
        return CreateDate;
    }
    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }


    public void setProducts(List<Product> product) {
        this.product = product;
    }

    public List<Product> getProducts() {
        return product;
    }
}
