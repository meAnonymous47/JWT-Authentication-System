package com.mukul.security.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mukul.security.Model.MyUsers;
import com.mukul.security.Service.UserService;

@RestController
public class UserController {
    
    private final UserService service;
    UserController(UserService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody MyUsers user){
        return service.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MyUsers user){
        // System.out.println("In login");
        return service.verify(user);
    }

}
