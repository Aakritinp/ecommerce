package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.services.ProductCatalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductCatalog productCatalog;
    
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
    	logger.info("User registered successfully" + product.getName());
    	return productCatalog.saveProduct(product);
    }
    
    // a. Browsing - Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productCatalog.getAllProducts();
    }

    // b. Product Details - Get product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productCatalog.getProductById(id);
    }

    // c. Search - Search products by name
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        return productCatalog.searchProducts(keyword);
    }
}

