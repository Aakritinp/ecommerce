package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;

  @Column(nullable = false)
  private String orderStatus;

  @Column(nullable = false)
  private Date orderDate;

  @Column(nullable = false)
  private Double totalAmount;

  @Column(nullable = false)
  private String shippingAddress;

  @Column(nullable = false)
  private String shippingMethod;

  @Column(nullable = false)
  private String name;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id")
  @JsonBackReference("user-order-reference")
  private User user;

  @ManyToMany
  @JoinTable(
      name = "order_product",
      joinColumns = @JoinColumn(name = "order_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private List<Product> products;

  @OneToOne(mappedBy = "order")
  @JsonManagedReference("order-payment-reference")
  private Payment payment;
}
