package com.example.app1.controller;

import com.example.app1.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            User loggedInUser = (User) session.getAttribute("user");
            model.addAttribute("user", loggedInUser);

            return "dashboard";
        } else return "redirect:/login";
    }
}