package com.example.demo.Controller;

import com.example.demo.Model.Order;
import com.example.demo.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.createOrder(order.getProducts(), order.getUser(), order.getAddress());
        return ResponseEntity.ok(newOrder);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable String id) {
        Order order = orderService.deleteOrder(id);
        return ResponseEntity.ok(order);
    }

}
