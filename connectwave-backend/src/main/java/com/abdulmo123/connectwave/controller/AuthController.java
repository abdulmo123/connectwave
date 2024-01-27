package com.abdulmo123.connectwave.controller;

import com.abdulmo123.connectwave.entity.AppUserDetails;
import com.abdulmo123.connectwave.entity.User;
import com.abdulmo123.connectwave.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

    @Autowired
    private UserService userService;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String getLoginPage() {
        return "authentication successful";
    }
    /*public ResponseEntity<String> getLoginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            User currentUser = ((AppUserDetails) auth.getPrincipal()).getUser();
            return ResponseEntity.ok("User authenticated successfully. user: ==> " + currentUser);
        }
        else {
            return ResponseEntity.badRequest().body("Authentication failed!");
        }
    }*/

    @PostMapping("/api/v1/signup")
    public ResponseEntity<User> signupUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
