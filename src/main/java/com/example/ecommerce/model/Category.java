package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "category")
@NoArgsConstructor
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long categoryId;

  @Column(nullable = false, unique = true)
  private String categoryName;

  @OneToMany(mappedBy = "category")
  @JsonManagedReference("product-reference")
  private List<Product> products;

  public Category(Long categoryId) {
    this.categoryId = categoryId;
  }
}
