package com.example.ecommerce.controller.admin;

import com.example.ecommerce.enums.Roles;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.User;
import com.example.ecommerce.services.CategoryService;
import com.example.ecommerce.services.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController {

  @Autowired private CategoryService categoryService;
  @Autowired private UserService userService;

  /**
   * Create a new category
   *
   * @param category
   * @return
   */
  @PostMapping
  public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    return ResponseEntity.ok(categoryService.createCategory(category));
  }

  /**
   * get category by id
   *
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
    Optional<Category> category = categoryService.getCategoryById(id);
    return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Get all categories
   *
   * @return
   */
  @GetMapping
  public ResponseEntity<List<Category>> getAllCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }

  /**
   * Update a category
   *
   * @param id
   * @param category
   * @return
   */
  @PutMapping("/{id}")
  public ResponseEntity<Category> updateCategory(
      @PathVariable Long id, @RequestBody Category category) {
    Optional<Category> updatedCategory = categoryService.updateCategory(id, category);
    return updatedCategory
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Delete a category
   *
   * @param id
   * @return
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    if (categoryService.deleteCategory(id)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
