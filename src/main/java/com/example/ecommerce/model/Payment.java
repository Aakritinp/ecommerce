package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "payment")
@Getter
@Setter
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long paymentId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  @JsonBackReference("order-payment-reference")
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonBackReference("user-payment-reference")
  private User user;

  @Column(nullable = false)
  private Double amount;

  @Column(nullable = false)
  private Date paymentDate;

  @Column(nullable = true)
  private String paymentMethod;

  @Column(nullable = true)
  private String paymentStatus;

  @Column(nullable = false)
  private String billingAddress;

  @Column(nullable = false)
  private String billingName;

  @Column(nullable = false)
  private String billingPhone;

  @Column(nullable = false)
  private String cardNumber;
}
