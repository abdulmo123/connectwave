package com.abdulmo123.connectwave.controller;

import com.abdulmo123.connectwave.entity.SignupRequest;
import com.abdulmo123.connectwave.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/signup")
@CrossOrigin(origins = {"http://localhost:4200"})
public class SignupController {

    @Autowired
    private SignupService signupService;

    @PostMapping("/add")
    public String register(@RequestBody SignupRequest signupRequest) {
        return signupService.signup(signupRequest);
    }
}
