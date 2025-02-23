package com.example.ecommerce.controller.customer;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.services.CategoryService;
import com.example.ecommerce.services.ProductService;
import com.example.ecommerce.services.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductCatalogController {

  @Autowired private ProductService productService;

  @Autowired private UserService userService;

  @Autowired private CategoryService categoryService;

  /**
   * Browsing - Get all products
   *
   * @return
   */
  @GetMapping
  public ResponseEntity<List<Category>> browseProducts() {
    List<Category> categories = categoryService.getAllCategories();
    return ResponseEntity.ok(categories);
  }

  /**
   * Get product details
   *
   * @param productId
   * @return
   */
  @GetMapping("/{productId}")
  public ResponseEntity<Product> getProductDetails(@PathVariable Long productId) {

    Optional<Product> product = productService.getProductById(productId);
    return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Search - Search for products based on name or other criteria
   *
   * @param query
   * @param categoryId
   * @return
   */
  @GetMapping("/search")
  public ResponseEntity<List<Product>> searchProducts(
      @RequestParam(value = "query", required = false) String query,
      @RequestParam(value = "category", required = false) Long categoryId) {
    List<Product> products = productService.searchProducts(query, categoryId);
    return ResponseEntity.ok(products);
  }
}
