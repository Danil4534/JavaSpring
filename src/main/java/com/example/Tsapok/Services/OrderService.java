package com.example.Tsapok.Services;

import com.example.Tsapok.Model.Order;
import com.example.Tsapok.Model.Product;
import com.example.Tsapok.OrderRepository;
import com.example.Tsapok.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    public Order createOrder(List<UUID> productsId) {
        Order order = new Order();
        for (UUID productId : productsId) {
            Product product = productService.findById(productId);
            order.getProducts().add(product);
        }
        return orderRepository.save(order);
    }

    public Order updateOrder(UUID id,Order order) {
        Order oldOrder = this.getOrderById(id);
        oldOrder.setStatus("Update");
        oldOrder.setCreateDate(order.getCreateDate());
        oldOrder.setProducts(order.getProducts());
        oldOrder.setUserId(oldOrder.getUserId());
        return orderRepository.save(oldOrder);
    }

    public Order deleteOrderById(UUID id) {
      Order order = getOrderById(id);
      orderRepository.delete(order);
      return order;
    }


}
