package com.example.Tsapok.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebsocketController {

    @GetMapping("/websocket")
    public String websocket() {
     return "WebSocket";
    }
}
