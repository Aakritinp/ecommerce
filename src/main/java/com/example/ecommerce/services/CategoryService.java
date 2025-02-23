package com.example.ecommerce.services;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  @Autowired private CategoryRepository categoryRepository;

  public Category createCategory(Category category) {
    return categoryRepository.save(category);
  }

  public Optional<Category> getCategoryById(Long id) {
    return categoryRepository.findById(id);
  }

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public Optional<Category> updateCategory(Long id, Category category) {
    if (categoryRepository.existsById(id)) {
      category.setCategoryId(id); // Ensure we're updating the correct category
      return Optional.of(categoryRepository.save(category));
    }
    return Optional.empty();
  }

  public boolean deleteCategory(Long id) {
    if (categoryRepository.existsById(id)) {
      categoryRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
