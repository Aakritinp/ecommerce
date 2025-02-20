package com.example.ecommerce.model;

import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(nullable = false)
    private String paymentMethod;
}
