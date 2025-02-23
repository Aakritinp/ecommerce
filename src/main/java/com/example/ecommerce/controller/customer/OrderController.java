package com.example.ecommerce.controller.customer;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;
import com.example.ecommerce.services.OrderService;
import com.example.ecommerce.services.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders/")
public class OrderController {

  @Autowired private OrderService orderService;

  @Autowired private UserService userService;

  /**
   * Order Tracking
   *
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
    Optional<Order> order = orderService.getOrderById(id);
    return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Order History
   *
   * @return
   */
  @GetMapping("/users/{userId}")
  public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
    User user = userService.findByUserId(userId);
    if (user == null) {
      return ResponseEntity.status(401).build();
    }
    List<Order> orders = orderService.getAllOrdersByUserId(user);
    return ResponseEntity.ok(orders);
  }
}
