package com.example.demo.Controllers;

import com.example.demo.Models.Order;
import com.example.demo.Services.OrderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public Flux<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/order/getOrderStatus/{id}")
    public Mono<String> getOrderStatus(@PathVariable String id) {
        return orderService.getOrderStatus(id);
    }

    @GetMapping("/order/{id}")
    public Mono<Order> getOrderById(@PathVariable("id") String id) {
        return orderService.getOrder(id);
    }

    @PostMapping( "/order/createOrder/{userId}/{address}/{productId}")
    public Mono<Order> createOrder(@PathVariable String userId, @PathVariable String address, @PathVariable String productId) {
        return orderService.createOrder(userId, address, productId);
    }

    @PutMapping("/updateOrderAddress/{id}/{address}")
    public Mono<Order> updateOrderAddress(@PathVariable String id, @PathVariable String address) {
        return orderService.updateOrderAddress(id, address);
    }
    @DeleteMapping("/deleteOrder/{id}")
    public Mono<Order> deleteOrder(@PathVariable String id) {
        return orderService.deleteOrder(id);
    }
}
