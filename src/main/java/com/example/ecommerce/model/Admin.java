package com.example.ecommerce.model;

import jakarta.persistence.*;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;
}