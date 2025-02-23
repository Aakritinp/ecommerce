package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Double price;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private Integer quantity;

  @ManyToOne(optional = false)
  @JoinColumn(name = "category_id")
  @JsonBackReference("product-reference")
  private Category category;

  @ManyToMany(mappedBy = "products")
  private List<Order> orders;

  @OneToMany(mappedBy = "product")
  @JsonManagedReference("product-cart-item-reference")
  private List<CartItem> cartItems;
}

