package com.example.app1.controller;

import com.example.app1.model.User;
import com.example.app1.repository.UserRepository;
import com.example.app1.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserService userService, UserRepository userRepository){
        this.userService=userService;
        this.userRepository=userRepository;
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, HttpSession httpSession, Model model){
        if(userService.authentication(user)) {
            User dbUser = userRepository.findByUsername(user.getUsername());

            httpSession.setAttribute("userId", dbUser.getUserId());
            httpSession.setAttribute("user", user);

            return "redirect:/dashboard";
        }
        else {
            model.addAttribute("error", true);
            return "login";
        }
    }
}
