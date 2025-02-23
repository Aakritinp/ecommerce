package com.example.ecommerce.services;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired private ProductRepository productRepository;

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Optional<Product> updateProduct(Long id, Product product) {
    if (productRepository.existsById(id)) {
      product.setProductId(id);
      return Optional.of(productRepository.save(product));
    }
    return Optional.empty();
  }

  public boolean deleteProduct(Long id) {
    if (productRepository.existsById(id)) {
      productRepository.deleteById(id);
      return true;
    }
    return false;
  }

  public List<Product> searchProducts(String query, Long categoryId) {
    if (query != null && !query.isEmpty() && categoryId != null) {
      return productRepository.findByNameContainingIgnoreCaseAndCategory(
          query, new Category(categoryId));
    } else {
      return productRepository.findAll();
    }
  }
}
