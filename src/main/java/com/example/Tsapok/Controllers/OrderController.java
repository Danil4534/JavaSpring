package com.example.Tsapok.Controllers;

import com.example.Tsapok.Model.Order;
import com.example.Tsapok.OrderRepository;
import com.example.Tsapok.Services.OrderService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/orders")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<Order>> orders() {
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }
    @PostMapping("/createOrder/{products}")
    public ResponseEntity<Order> createOrder( @PathVariable List<UUID> products) {
        Order order = orderService.createOrder(products);
        return ResponseEntity.ok(order);
    }
    @PutMapping("/updateStatus/{id}/{status}")
    public ResponseEntity<Order> updateStatus(@PathVariable UUID id, @PathVariable String status) {
        Order order = orderService.getOrderById(id);
        order.setStatus(status);
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable UUID id, @RequestBody Order order) {
       try{
           Order order1 = orderService.updateOrder(id, order);
           return ResponseEntity.ok(order1);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }
    @DeleteMapping("/deleteOrder")
    public ResponseEntity<Order> deleteOrder(@PathVariable UUID id) {
        try{
            Order order = orderService.getOrderById(id);
            orderService.deleteOrderById(id);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
