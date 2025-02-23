package com.example.ecommerce.services;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired private OrderRepository orderRepository;

  public Order createOrder(Order order) {
    return orderRepository.save(order);
  }

  public Optional<Order> getOrderById(Long id) {
    return orderRepository.findById(id);
  }

  public List<Order> getAllOrdersByUserId(User user) {
    return orderRepository.findByUser(user);
  }

  public Optional<Order> updateOrder(Long id, Order order) {
    if (orderRepository.existsById(id)) {
      order.setOrderId(id);
      return Optional.of(orderRepository.save(order));
    }
    return Optional.empty();
  }
}
