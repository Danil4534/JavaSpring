package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping
public class OrderController {

    @Autowired
    private EmailService emailService;

    private  WebClient webClient;
    public OrderController(WebClient.Builder  webClientBuilder) {
        String apiKey = System.getenv("API_KEY");
        this.webClient=webClientBuilder.baseUrl("https://" + apiKey + ".mockapi.io/api/v1/order").build();
    }

    @GetMapping("/order")
    public Mono<ResponseEntity<JsonNode>> getOrders() {
        return webClient.get()
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/order/getOrderStatus/{id}")
    public Mono<ResponseEntity<String>> getOrderStatus(@PathVariable("id") String id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(response -> {
                    String status = response.path("status").asText();
                    return ResponseEntity.ok(status);
                });
    }

    @GetMapping("/order/{id}")
    public Mono<ResponseEntity<JsonNode>> getUser(@PathVariable String id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }
    @PostMapping("/order/createOrder")
    public Mono<ResponseEntity<JsonNode>> createOrder(@RequestBody Order order) {
        return webClient.post()
                .bodyValue(order)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/order/editAddress/{orderId}")
    public Mono<ResponseEntity<String>> editAddress(@PathVariable String orderId, @RequestBody String address) {
        return webClient.put()
                .uri("/{id}",orderId)
                .bodyValue(address)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> ResponseEntity.ok(response));
    }


    @PutMapping("/order/editOrder/{id}")
    public Mono<ResponseEntity<JsonNode>> editUser(@PathVariable String id, @RequestBody Order order) {
        return  webClient.put()
                .uri("/{id}", id)
                .bodyValue(order)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }
    @PutMapping("/order/deleteOrder/{id}")
    public Mono<ResponseEntity<JsonNode>> deleleOrder(@PathVariable String id,@RequestBody Map<String, String> status) {
        return webClient.put()
                .uri("/{id}", id)
                .bodyValue(status)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(ResponseEntity::ok);
    }

}

@Aspect
@Component
class LoggingAspect {
    private final EmailService emailService;

    public LoggingAspect(EmailService emailService) {
        this.emailService = emailService;
    }

    @AfterReturning(pointcut= "execution(* com.example.demo.OrderController.*(..))", returning = "result")
    public void logMethodArguments(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String logMessage = "Method executed "+ methodName + "Arguments: " + Arrays.toString(args);
        emailService.sendSimpleMail("OrderController Log", logMessage, System.getenv("EMAIL"));
        System.out.println("Method executed "+ methodName + "Arguments: " + Arrays.toString(args));
        System.out.println("Returned:"+ result);
    }
}

@Aspect
@Component
class OrderStatusAspect {
    @Before("execution(* com.example.demo.OrderController.createOrder(..)) && args(order)")
    public void setPendingStatusBeforeSave(Order order) {
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("PENDING");
        }
    }
}
