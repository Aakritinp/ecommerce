package com.example.ecommerce.model;

import jakarta.persistence.*;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private String orderStatus;

    @Column(nullable = false)
    private String orderDate;

    @ManyToOne(optional = false)
    private Customer customer;
}
