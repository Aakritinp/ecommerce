package com.example.ecommerce.controller.customer;

import com.example.ecommerce.model.Payment;
import com.example.ecommerce.model.User;
import com.example.ecommerce.services.PaymentService;
import com.example.ecommerce.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments/")
public class PaymentController {

  @Autowired private PaymentService paymentService;

  @Autowired private UserService userService;

  /**
   * Process payment
   *
   * @param userId
   * @param orderId
   * @return
   */
  @PostMapping("orders/{orderId}/users/{userId}")
  public ResponseEntity<Payment> processPayment(
      @PathVariable Long userId, @PathVariable Long orderId) {
    User user = userService.findByUserId(userId);
    if (user == null) {
      return ResponseEntity.status(401).build();
    }
    return ResponseEntity.ok(paymentService.processPayment(orderId, user));
  }

  /**
   * Payment receipt
   *
   * @param paymentId
   * @return
   */
  @GetMapping("/{paymentId}")
  public ResponseEntity<Payment> getPaymentDetails(@PathVariable Long paymentId) {
    return ResponseEntity.ok(paymentService.getPaymentDetails(paymentId));
  }

  /**
   * Multiple payment options
   *
   * @return
   */
  @GetMapping("/options")
  public ResponseEntity<List<String>> getPaymentOptions() {
    return ResponseEntity.ok(
        List.of("Credit Card", "Debit Card", "PayPal", "Google Pay", "Apple Pay"));
  }
}
