package com.example.Tsapok.Controllers;

import com.example.Tsapok.Model.Order;
import com.example.Tsapok.Model.User;
import com.example.Tsapok.OrderRepository;
import com.example.Tsapok.Services.OrderService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/orders")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @GetMapping()
    public ResponseEntity<List<Order>> orders() {
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/orderStatus/{orderId}")
    public ResponseEntity<String> getOrderStatus(@PathVariable Long orderId) {
        String status = orderService.getOrderStatus(orderId);
        return ResponseEntity.ok(status);
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/createOrder/{productId}/{userId}/{address}")
    public ResponseEntity<Order> createOrder( @PathVariable Long productId, @PathVariable Long userId, @PathVariable String address) {
        Order order = orderService.createOrder(productId, userId, address);
        return ResponseEntity.ok(order);
    }
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/updateAddress/{id}/{address}")
    public ResponseEntity<Order> updateAddress(@PathVariable Long id, @PathVariable String address) {
        Order order = orderService.getOrderById(id);
        order.setAddress(address);
        orderRepository.save(order);
        return ResponseEntity.ok(order);
    }
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
       try{
           Order order1 = orderService.updateOrder(id, order);
           return ResponseEntity.ok(order1);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
        try{
            Order order = orderService.getOrderById(id);
            orderService.deleteOrderById(id);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
