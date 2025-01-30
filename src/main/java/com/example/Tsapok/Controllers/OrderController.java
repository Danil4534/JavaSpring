package com.example.Tsapok.Controllers;

import com.example.Tsapok.Model.Order;

import com.example.Tsapok.OrderRepository;
import com.example.Tsapok.Services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;



@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Operation(summary = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of orders"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @RequestMapping(method = RequestMethod.GET)
    public String orders(Model model) {
        List<Order> orders = orderService.getOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }


//   @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<String> getOrderStatus(@PathVariable Long orderId) {
//        String status = orderService.getOrderStatus(orderId);
//        return ResponseEntity.ok(status);
//    }
@Operation(summary = "Create a new order")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created a product"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
})
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
    @PostMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id) {
            orderService.deleteOrderById(id);
            return "redirect:/orders";
    }
}
