package com.app.HealthApp.healthapp.controller;

import com.app.HealthApp.healthapp.model.User;
import com.app.HealthApp.healthapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registrationPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password
    ){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        userService.registerUser(user);
        return "redirect:/login?success=true";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


}
