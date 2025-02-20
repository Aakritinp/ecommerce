package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	// Search products by name (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);
    
  
}
