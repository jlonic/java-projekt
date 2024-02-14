package com.example.app1.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {
    @PostMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();

        return "redirect:/login";
    }
}
