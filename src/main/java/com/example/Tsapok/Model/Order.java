package com.example.Tsapok.Model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Optional;


@Entity
@Table(name = "\"Orders\"")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;



    @OneToOne
    @JoinColumn(name = "products")
    private Product product;

    @OneToOne
    @JoinColumn(name ="users")
    private User user;

    @Column(name="status")
    private String status;

    @Column(name="address")
    private String address;

    @Column(name = "CreateDate")
    private Date CreateDate;


    public Order(User user, Product product, String status, String address) {
        this.user = user;
        this.product = product;
        this.status = status;
        this.address = address;
        this.CreateDate = new Date();

    }
    public Order() {}


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public String getAddress() {
        return address;
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


    public void setAddress(String address) {
        this.address = address;
    }
}
