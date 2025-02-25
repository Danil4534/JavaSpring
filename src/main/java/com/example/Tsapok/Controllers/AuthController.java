package com.example.Tsapok.Controllers;

import com.example.Tsapok.Enum.Role;
import com.example.Tsapok.Model.User;
import com.example.Tsapok.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;

@Controller
public class AuthController {


    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "RegisterForm";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model ) throws NoSuchAlgorithmException {
        if (result.hasErrors()) {
            return "RegisterForm";
        }
        user.setRoles(Collections.singletonList(Role.ROLE_USER));
        User registerUser=userService.register(user.getUsername(), user.getPassword(),user.getRoles());
        if (registerUser == null) {
            model.addAttribute("RegisterError", "Invalid email or password.");
            return "RegisterForm";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "LoginForm";
    }

    @PostMapping("/login")
    public String login( @ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "LoginForm";
        }

        String userLogin = userService.login(user.getUsername(), user.getPassword());
        if (userLogin == null) {
            model.addAttribute("loginError", "Invalid email or password.");
            return "LoginForm";
        }

        return "/orders";
    }

}
