package com.example.ecommerce.model;

import com.example.ecommerce.enums.Roles;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private Roles role;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String shippingAddress;

  @Column(nullable = false)
  private String billingAddress;

  @Column(nullable = false)
  private String cardNumber;

  @Column(nullable = false)
  private String expirationDate;

  @Column(nullable = false)
  private String billingName;

  @Column(nullable = false)
  private String billingPhone;

  @OneToMany(mappedBy = "user")
  @JsonManagedReference("user-order-reference")
  private List<Order> orders;

  @OneToOne(mappedBy = "user")
  @JsonManagedReference("cart-reference")
  private Cart cart;

  @OneToMany(mappedBy = "user")
  @JsonManagedReference("user-payment-reference")
  private List<Payment> payments;

  public User(Long userId) {
    this.userId = userId;
  }
}
