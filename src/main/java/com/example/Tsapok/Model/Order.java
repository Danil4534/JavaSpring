package com.example.Tsapok.Model;

import jakarta.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @OneToMany
    @JoinColumn(name="product_id")
    private List<Product> products;

    @Column(name="status")
    private String status;

    @Column(name = "CreateDate")
    private Date CreateDate;

    public Order() {}

  public Order(User userId, List<Product> products, String status, Date CreateDate) {
        this.userId = userId;
        this.products = products;
        this.status = status;
        this.CreateDate = CreateDate;
  }

    public void setUserId(User userId) {
        this.userId = userId;
    }
    public User getUserId() {
        return userId;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
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
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }







}
