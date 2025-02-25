package com.example.Tsapok;

import com.example.Tsapok.Enum.Role;
import com.example.Tsapok.Model.Product;

import com.example.Tsapok.Services.ProductService;
import com.example.Tsapok.Services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ApplicationContext context) {
		return args -> {
			UserService userService = context.getBean(UserService.class);
			ProductService productService = context.getBean(ProductService.class);
			Product product = new Product("Apple", "MacBook", 35000, 2);

			productService.createProduct(product);
			List<Role> roles = List.of(Role.ROLE_ADMIN, Role.ROLE_USER);
			userService.register("test", "testtest", roles);
		};
	}


}