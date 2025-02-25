package com.example.ecommerce.services;

import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired private UserRepository userRepository;

  public User registerUser(User user) {
    if (userRepository.findByUsername(user.getUsername()) != null) {
      return null;
    }
    return userRepository.save(user);
  }

  public User loginUser(String username, String password) {
    User user = userRepository.findByUsername(username);
    if (user != null && user.getPassword().equals(password)) {

      return user;

    }
    return null;
  }

  public User findByUserId(Long userId) {
    return userRepository
        .findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  public User resetPassword(String username, String newPassword, String oldPassword) {
    User user = userRepository.findByUsername(username);
    if (user != null && user.getPassword().equals(oldPassword)) {
      user.setPassword(newPassword);
      return userRepository.save(user);
    }
    return null;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}
