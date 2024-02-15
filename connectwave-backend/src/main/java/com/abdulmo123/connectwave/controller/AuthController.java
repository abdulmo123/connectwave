package com.abdulmo123.connectwave.controller;

import com.abdulmo123.connectwave.entity.User;
import com.abdulmo123.connectwave.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "authentication successful";
    }

    @PostMapping("/api/v1/signup")
    public ResponseEntity<User> signupUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
