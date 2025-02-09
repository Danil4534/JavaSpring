package com.example.Tsapok.Controllers;

import com.example.Tsapok.Model.Order;

import com.example.Tsapok.Model.Product;
import com.example.Tsapok.Model.User;
import com.example.Tsapok.OrderRepository;
import com.example.Tsapok.Services.OrderService;
import com.example.Tsapok.Services.ProductService;
import com.example.Tsapok.Services.UserService;
import com.example.Tsapok.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @Autowired
    private ProductService productService;
    @Autowired
    private OrderRepository orderRepository;


    @RequestMapping(method = RequestMethod.GET)
    public String orders(Model model) {
        List<Order> orders = orderService.getOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/createOrder")
    public String createOrder(Model model) {
        List<Product> products = productService.findAll();
        List<User> users = userService.GetAllUsers();
        model.addAttribute("order", new Order());
        model.addAttribute("users", users);
        model.addAttribute("products",products );
        return "createOrder";
    }



    @PostMapping("/createOrder")
    public String createOrder(@ModelAttribute("order") @Valid Order order, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "createOrder";
        }
         Order newOrder = orderService.createOrder(order.getProducts(),order.getUser(), order.getAddress());
        if (newOrder == null) {
            model.addAttribute("order", "Error with creating order");
        }
        return "redirect:/orders";
    }


//   @PostMapping("/getOrderStatus/{orderId}")
//    public ResponseEntity<String> getOrderStatus(@PathVariable Long orderId) {
//        String status = orderService.getOrderStatus(orderId);
//        return ResponseEntity.ok(status);
//    }
//
//
//    @GetMapping("/createOrder")
//    public String showCreateOrderForm(Model model) {
//        List<User> users = userService.GetAllUsers();
//        List<Product> products = productService.findAll();
//         Order order = new Order();
//         model.addAttribute("order", order);
//        model.addAttribute("users", users);
//        model.addAttribute("products", products);
//        return "createOrder";
//    }
//
//    @PostMapping("/makeOrder")
//    public String makeOrder(@ModelAttribute Order order, @RequestParam List<Long> productIds) {
//        Order newOrder = orderService.createOrder(productIds, order.getUserId(), order.getAddress());
//        if(newOrder != null) {
//            return "redirect:/orders";
//        }
//        return "redirect:/orders";
//
//    }
//
//
//    @PutMapping("/updateAddress/{id}/{address}")
//    public ResponseEntity<Order> updateAddress(@PathVariable Long id, @PathVariable String address) {
//        Order order = orderService.getOrderById(id);
//        order.setAddress(address);
//        orderRepository.save(order);
//        return ResponseEntity.ok(order);
//    }
////    @PreAuthorize("isAuthenticated()")
////    @PutMapping("/updateOrder/{id}")
////    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
////       try{
////           Order order1 = orderService.updateOrder(id, order);
////           return ResponseEntity.ok(order1);
////       } catch (Exception e) {
////           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
////       }
////    }
//
    @PostMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        order.setStatus("deleted");
        orderRepository.save(order);
        return "redirect:/orders";
    }
}
