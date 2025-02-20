package com.example.ecommerce.model;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;



    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;
}
