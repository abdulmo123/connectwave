package com.abdulmo123.connectwave.service;

public interface EmailService {

    void sendEmail(String to, String subject, String message);


    void sendForgotPasswordEmail(String to);
}
