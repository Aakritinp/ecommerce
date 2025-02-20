package com.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false, unique = true)
    private String categoryName;

    
    public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}
