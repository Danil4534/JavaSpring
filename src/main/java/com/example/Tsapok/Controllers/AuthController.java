package com.example.Tsapok.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping("/")
@Controller
public class AuthController {
    @RequestMapping(method = RequestMethod.GET)
    public String getLoginForm(){
        return "LoginForm";
    }

    @GetMapping("/register")
    public String getRegisterForm(){
        return "RegisterForm";
    }

}
