package com.example.ecommerce.services;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCatalog {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setDescription(productDetails.getDescription());
            product.setQuantity(productDetails.getQuantity());
            product.setCategory(productDetails.getCategory());
            return productRepository.save(product);
        }).orElse(null);
    }
    
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
    
    public void deleteById(Long id) {
    	productRepository.deleteById(id);
    }
    
    
    
}
