package com.example.app1.controller;

import com.example.app1.model.User;
import com.example.app1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/add")
    public void addUser(User user){userService.addUser(user); }
}
