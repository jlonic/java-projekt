package com.example.app1.controller;

import com.example.app1.model.User;
import com.example.app1.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService){ this.userService=userService;}

    @GetMapping("/registration")
    public String showRegistrationForm(Model model, HttpSession httpSession){
        if (httpSession.getAttribute("user") != null)
            return "redirect:/dashboard";

        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String login(@ModelAttribute("user") User user, Model model){
        if(userService.isUsernameTaken(user.getUsername())){
            model.addAttribute("error", true);
            return "registration";
        }
        else {
            userService.addUser(user);
            return "redirect:/login";
        }
    }
}