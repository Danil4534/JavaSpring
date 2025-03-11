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

    @GetMapping("/findOrder/{id}")
    public ResponseEntity<Order> findOrder(@PathVariable String id) {
        Order findingOrder = orderService.getOrderById(id);
        return ResponseEntity.ok(findingOrder);
    }

    @GetMapping("/order/getOrderStatus/{id}")
    public ResponseEntity<String> getOrderStatus(@PathVariable String id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order.getStatus());
    }


    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    @PostMapping("/createOrder/{userId}/{address}/{productId}")
    public ResponseEntity<Order> createOrder(@PathVariable String userId, @PathVariable String address, @PathVariable String productId) {
        Order newOrder = orderService.createOrder(userId, productId,address);
        return ResponseEntity.ok(newOrder);
    }
    @PutMapping("/updateOrderAddress/{id}/{address}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id,@PathVariable String address) {
        Order existingOrder = orderService.getOrderById(id);
        existingOrder.setAddress(address);
        return ResponseEntity.ok(existingOrder);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable String id) {
        Order order = orderService.deleteOrder(id);
        return ResponseEntity.ok(order);
    }

}
