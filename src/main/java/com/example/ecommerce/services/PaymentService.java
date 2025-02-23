package com.example.ecommerce.services;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Payment;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.PaymentRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  @Autowired private PaymentRepository paymentRepository;

  @Autowired private OrderService orderService;

  public Payment processPayment(Long orderId, User user) {
    Order order =
        orderService
            .getOrderById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
    Payment existingPayment = paymentRepository.findByOrder(order);

    if (existingPayment != null) {
      return existingPayment;
    }

    Payment payment = new Payment();
    payment.setUser(order.getUser());
    payment.setOrder(order);
    payment.setPaymentDate(new Date());
    payment.setAmount(order.getTotalAmount());
    payment.setPaymentStatus("COMPLETED");
    payment.setBillingAddress(user.getBillingAddress());
    payment.setCardNumber("1234-5678-9012-3456");
    payment.setBillingName(user.getUsername());
    payment.setBillingPhone(user.getPhone());
    payment.setPaymentMethod("Credit Card");
    payment.setCardNumber(user.getCardNumber());
    payment.setBillingName(user.getBillingName());
    payment.setBillingPhone(user.getBillingPhone());
    payment = paymentRepository.save(payment);

    // Update the order status to "PAID"
    order.setOrderStatus("PAID");
    orderService.updateOrder(orderId, order);

    return payment;
  }

  public Payment getPaymentDetails(Long paymentId) {
    return paymentRepository
        .findById(paymentId)
        .orElseThrow(() -> new RuntimeException("Payment not found"));
  }
}
