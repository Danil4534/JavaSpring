package com.example.Tsapok.Services;

import com.example.Tsapok.Model.Order;
import com.example.Tsapok.Model.Product;
import com.example.Tsapok.Model.User;
import com.example.Tsapok.OrderRepository;
import com.example.Tsapok.ProductRepository;
import com.example.Tsapok.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    public Order createOrder(Long productId, Long userId, String address) {
        Product product= productService.findById(productId);
        User user = userService.getUserById(userId);
        Order order = new Order(user,product,"create",address);
        return orderRepository.save(order);
    }


    public String getOrderStatus(Long id) {
       Optional<Order> order = orderRepository.findById(id);
       return order.map(Order::getStatus).orElse(null);
    }
    public Order updateOrder(Long id,Order order) {
        Order oldOrder = this.getOrderById(id);
        oldOrder.setStatus("Update");
        oldOrder.setCreateDate(order.getCreateDate());
        oldOrder.setProduct(order.getProduct());
        return orderRepository.save(oldOrder);
    }

    public Order deleteOrderById(Long id) {
      Order order = getOrderById(id);
      orderRepository.delete(order);
      return order;
    }


}
