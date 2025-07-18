package com.mukul.security.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.mukul.security.Service.DukaanService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class DefaultController {

    @Autowired
    DukaanService service;

    @RequestMapping("/")
    public String Hello(HttpServletRequest request) {
        return "Hello This is the Default Page for Spring Security App " + 
        "And our session ID: " + request.getSession().getId();
    }

    @GetMapping("/csrf-token")
    public CsrfToken getMethodName(HttpServletRequest request) {
        return service.getCsrfToken(request);
    }
    
}
