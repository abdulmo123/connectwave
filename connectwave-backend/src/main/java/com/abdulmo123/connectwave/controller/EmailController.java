package com.abdulmo123.connectwave.controller;

import com.abdulmo123.connectwave.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendMail")
    public void sendMail() {
        emailService.sendEmail( "*@gmail.com", "Test email", "This is a test email.");
    }

    // TODO: send forgot password email
    @PostMapping("/sendForgotPwdEmail")
    public ResponseEntity<String> sendForgotPwdEmail(@RequestBody String email) {
        emailService.sendForgotPasswordEmail(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
