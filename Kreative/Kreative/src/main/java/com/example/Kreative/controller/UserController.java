package com.example.Kreative.controller;


import com.example.Kreative.model.User;
import com.example.Kreative.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return  userService.register(user);
    }


    @PostMapping("/login")
    public User login(@RequestBody User user){
        return userService.login(user);
    }
}
