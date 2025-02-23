package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonBackReference("cart-reference")
  private User user;

  @OneToMany(mappedBy = "cart")
  @JsonManagedReference("cart-item-reference")
  private List<CartItem> cartItems;

  public Double getTotalPrice() {
    double totalPrice = 0.0;
    for (CartItem item : cartItems) {
      totalPrice += item.getTotalPrice();
    }
    return totalPrice;
  }
}
