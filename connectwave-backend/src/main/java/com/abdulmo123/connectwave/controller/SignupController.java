package com.abdulmo123.connectwave.controller;

import com.abdulmo123.connectwave.entity.SignupRequest;
import com.abdulmo123.connectwave.service.SignupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/signup")
public class SignupController {

    private SignupService signupService;

    public String register(@RequestBody SignupRequest signupRequest) {
        return signupService.signup(signupRequest);
    }
}
