package com.example.SpringSecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller{
    @GetMapping("/")
    public String Home(){
        return "Home Page";
    }

    @GetMapping("/admin")
    public String AdminRole(){
        return " Admin Role";
    }

    @GetMapping("/user")
    public String UserRole(){
        return "User role";
    }
}