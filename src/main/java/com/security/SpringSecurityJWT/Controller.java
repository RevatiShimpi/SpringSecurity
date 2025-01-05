package com.security.SpringSecurityJWT;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller{
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
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