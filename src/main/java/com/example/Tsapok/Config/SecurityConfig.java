package com.example.Tsapok.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/orders/createOrder").permitAll()
                        .requestMatchers("/orders").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/orders/deleteOrder/**").hasAnyRole("ADMIN")
                        .requestMatchers("/orders/editOrder/**").hasAnyRole("ADMIN")

                        .anyRequest().authenticated()
                      )
                .logout(logout ->logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                .formLogin(form->form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/orders", true)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
