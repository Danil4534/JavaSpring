package com.example.Tsapok;

import com.example.Tsapok.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> id(UUID id);
}
