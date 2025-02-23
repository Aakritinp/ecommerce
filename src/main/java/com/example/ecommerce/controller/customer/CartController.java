package com.example.ecommerce.controller.customer;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.User;
import com.example.ecommerce.services.CartService;
import com.example.ecommerce.services.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

  @Autowired private CartService cartService;

  @Autowired private UserService userService;

  /**
   * Add product to cart
   *
   * @param userId
   * @param productId
   * @param quantity
   * @return
   */
  @PostMapping("/add")
  public ResponseEntity<Cart> addToCart(
      @RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
    User user = userService.findByUserId(userId);
    if (user == null) {
      return ResponseEntity.status(401).build();
    }
    Cart updatedCart = cartService.addProductToCart(user, productId, quantity);
    if (updatedCart == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(updatedCart);
  }

  /**
   * Cart Review
   *
   * @return
   */
  @GetMapping("/users/{userId}")
  public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
    User user = userService.findByUserId(userId);
    if (user == null) {
      return ResponseEntity.status(401).build();
    }
    Optional<Cart> cart = cartService.findCartByUser(user);
    return cart.isPresent() ? ResponseEntity.ok(cart.get()) : ResponseEntity.notFound().build();
  }

  /**
   * Checkout
   *
   * @return
   */
  @PostMapping("/checkout/users/{userId}")
  public ResponseEntity<String> checkout(
      @PathVariable Long userId, @RequestBody List<Long> cartItemIds) {
    User user = userService.findByUserId(userId);
    if (user == null) {
      return ResponseEntity.status(401).build();
    }
    String orderConfirmation = cartService.checkout(user, cartItemIds);
    return ResponseEntity.ok(orderConfirmation);
  }
}
