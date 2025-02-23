package com.example.ecommerce.services;

import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

  @Autowired private CartRepository cartRepository;

  @Autowired private CartItemRepository cartItemRepository;

  @Autowired private ProductRepository productRepository;

  @Autowired private OrderService orderService;

  // Add a Product to Cart (with quantity)
  public Cart addProductToCart(User user, Long productId, Integer quantity) {
    Optional<Cart> cartOpt = cartRepository.findByUser(user);
    if (cartOpt.isEmpty()) {
      Cart cart = new Cart();
      cart.setUser(user);
      cart = cartRepository.save(cart);
      cartOpt = Optional.of(cart);
    }
    Optional<Product> productOpt = productRepository.findById(productId);

    if (productOpt.isPresent()) {
      Cart cart = cartOpt.get();
      Product product = productOpt.get();

      // Check if the product already exists in the cart
      Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

      if (existingCartItem.isPresent()) {
        CartItem cartItem = existingCartItem.get();
        cartItem.setQuantity(
            cartItem.getQuantity() + quantity); // Increase quantity if the product already exists
        cartItemRepository.save(cartItem);
      } else {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(product.getPrice());
        cartItemRepository.save(cartItem);
      }
      return cart;
    }
    return null;
  }

  // Get Cart by User
  public Optional<Cart> findCartByUser(User user) {
    return cartRepository.findByUser(user);
  }

  public String checkout(User user, List<Long> cartItemIds) {
    // Get the user's cart
    List<CartItem> cartItems = cartItemRepository.findAllById(cartItemIds);

    Double totalPrice = 0.0;
    for (CartItem cartItem : cartItems) {
      totalPrice += cartItem.getTotalPrice();
    }

    Order order = new Order();
    order.setUser(user);
    order.setOrderStatus("PLACED");
    order.setOrderDate(new Date());
    order.setOrderStatus("PENDING");
    order.setTotalAmount(totalPrice);
    order.setShippingAddress(user.getShippingAddress());
    order.setShippingMethod("Standard");
    order.setName(user.getName());
    orderService.createOrder(order);

    Optional<Cart> cartOptional = cartRepository.findByUser(user);
    if (cartOptional.isPresent()) {
      Cart cart = cartOptional.get();
      cart.getCartItems().removeAll(cartItems);
      cartRepository.save(cart);
      cartItemRepository.deleteAll(cartItems);
    }

    return "Order placed. Order ID: "
        + order.getOrderId()
        + " Please pay the amount: "
        + order.getTotalAmount();
  }
}
