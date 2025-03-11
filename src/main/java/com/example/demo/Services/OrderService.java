package com.example.demo.Services;

import com.example.demo.Model.Order;
import com.example.demo.Model.Product;
import com.example.demo.Model.User;
import com.example.demo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    public Order getOrderById(String id) {
        Optional <Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    public Order createOrder(String userId, String productId, String address) {
        User user = userService.findUserById(userId);
        Product product = productService.findById(productId);
        Order order = new Order(user,product,"created", address);
        return orderRepository.save(order);
    }
    public Order updateOrder(Order order) {
        return orderRepository.save(order);

    }
    public Order deleteOrder(String id) {
        Order DeleteOrder = orderRepository.findById(id).get();
        orderRepository.deleteById(id);
        return DeleteOrder;
    }

}
