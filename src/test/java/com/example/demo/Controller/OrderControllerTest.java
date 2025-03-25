package com.example.demo.Controller;

import com.example.demo.Model.Order;

import com.example.demo.Services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void findOrder() throws Exception {
        String orderId = "67d05fad7573555046acb57f";
        mockMvc.perform(get("/findOrder/{id}", orderId))
                .andExpect(status().isOk());
    }
    @Test
    void getOrderStatus() throws Exception {
        String orderId = "67d05fad7573555046acb57f";
        Order order = new Order();
        order.setId(orderId);
        order.setStatus("PENDING");
        when(orderService.getOrderById(orderId)).thenReturn(order);
        mockMvc.perform(get("/order/getOrderStatus/{id}", orderId))
                .andExpect(status().isOk())
                .andExpect(content().string("PENDING"));
    }

    @Test
    void getOrders() throws Exception {
        Order order1 = new Order();
        order1.setId("67d05fad7573555046acb57f");
        order1.setAddress("123 Street");
        Order order2 = new Order();
        order2.setId("67cf2722608ecd192aa895e9");
        order2.setAddress("456 Avenue");
        List<Order> orders = Arrays.asList(order1, order2);
        when(orderService.getAllOrders()).thenReturn(orders);
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(order1.getId()))
                .andExpect(jsonPath("$[1].id").value(order2.getId()))
                .andExpect(jsonPath("$[0].address").value(order1.getAddress()))
                .andExpect(jsonPath("$[1].address").value(order2.getAddress()));
    }
    @Test
    void createOrder() throws Exception {
        String userId = "67cf2722608ecd192aa895e9";
        String address = "123 Street";
        String productId = "67cfff971b26156bee22460a";
        mockMvc.perform(post("/createOrder/{userId}/{address}/{productId}",userId,address,productId))
            .andExpect(status().isOk());
    }

    @Test
    void updateOrder() throws Exception {
        String orderId = "67d05fad7573555046acb57f";
        String newAddress = "456 New Street";
        Order existingOrder = new Order();
        existingOrder.setId(orderId);
        existingOrder.setAddress("123 Old Street");
        existingOrder.setCreateDate(new Date());
        when(orderService.getOrderById(orderId)).thenReturn(existingOrder);
        mockMvc.perform(put("/updateOrderAddress/{id}/{address}", orderId, newAddress))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderId))
                .andExpect(jsonPath("$.address").value(newAddress));
    }

    @Test
    void deleteOrder() throws Exception {
        String orderId = "67d05fad7573555046acb57f";
        Order order = new Order();
        order.setId(orderId);
        order.setStatus("PENDING");
        order.setAddress("123 Street");
        when(orderService.deleteOrder(orderId)).thenReturn(order);
        mockMvc.perform(delete("/deleteOrder/{id}", orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderId))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.address").value("123 Street"));
    }
}