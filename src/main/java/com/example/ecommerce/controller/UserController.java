package com.example.ecommerce.controller;

import com.example.ecommerce.model.User;
import com.example.ecommerce.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  Logger logger = LoggerFactory.getLogger(UserController.class);
  @Autowired private UserService userService;

  /**
   * User registration
   *
   * @param user
   * @return
   */
  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody User user) {
    logger.info("User registered successfully" + user.getName());
    user = userService.registerUser(user);
    if (user == null) {
      return ResponseEntity.badRequest().body("User already exists");
    }

    return ResponseEntity.ok("User registered successfully with id: " + user.getUserId());
  }

  /**
   * Login User
   *
   * @param username
   * @param password
   * @return
   */
  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password) {

    User user = userService.loginUser(username, password);
    if (user == null) {
      return ResponseEntity.badRequest().body("Invalid username or password");
    }
    return ResponseEntity.ok(user);
  }

  /**
   * Reset Password
   *
   * @param username
   * @param newPassword
   * @param oldPassword
   * @return
   */
  @PatchMapping("/reset-password")
  public ResponseEntity<?> resetPassword(
      @RequestParam String username,
      @RequestParam String newPassword,
      @RequestParam String oldPassword) {
    User user = userService.resetPassword(username, newPassword, oldPassword);
    if (user == null) {
      return ResponseEntity.badRequest().body("Invalid username or password");
    }
    return ResponseEntity.ok(user);
  }
}
