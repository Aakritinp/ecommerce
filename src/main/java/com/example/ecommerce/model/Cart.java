package com.example.ecommerce.model;

import jakarta.persistence.Entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;  
    
    private List<Product> items;  

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }
}
