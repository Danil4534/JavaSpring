package com.example.Tsapok;

import com.example.Tsapok.Model.Order;
import com.example.Tsapok.Model.Product;
import com.example.Tsapok.Model.User;
import com.example.Tsapok.Services.ProductService;
import com.example.Tsapok.Services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ApplicationContext context) {
		return args -> {
			UserService userService = context.getBean(UserService.class);
			User user = new User("Danil", "123", "123", new ArrayList<Order>());
			ProductService productService = context.getBean(ProductService.class);
			Product product = new Product("Apple", "MacBook", 35000, 2);
			productService.createProduct(product);
//			userService.createUser(user);
		};
	}


}