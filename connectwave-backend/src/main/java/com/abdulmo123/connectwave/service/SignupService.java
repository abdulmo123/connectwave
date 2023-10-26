package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.entity.SignupRequest;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    public String signup(SignupRequest signupRequest) {
        return "it works!";
    }
}
