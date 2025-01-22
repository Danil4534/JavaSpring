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
    private UserRepository userRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }


    public String getOrderStatus(UUID id) {
       Optional<Order> order = orderRepository.findById(id);
       return order.map(Order::getStatus).orElse(null);
    }
    public Order updateOrder(UUID id,Order order) {
        Order oldOrder = this.getOrderById(id);
        oldOrder.setStatus("Update");
        oldOrder.setCreateDate(order.getCreateDate());
        oldOrder.setProducts(order.getProducts());
        return orderRepository.save(oldOrder);
    }

    public Order deleteOrderById(UUID id) {
      Order order = getOrderById(id);
      orderRepository.delete(order);
      return order;
    }


}
