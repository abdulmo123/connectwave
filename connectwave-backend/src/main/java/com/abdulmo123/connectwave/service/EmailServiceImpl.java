package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.entity.User;
import com.abdulmo123.connectwave.repository.UserRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemplateEngine templateEngine;

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Async
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage signUpEmail = new SimpleMailMessage();

        signUpEmail.setFrom("abdullah2224@gmail.com");
        signUpEmail.setTo(to);
        signUpEmail.setSubject(subject);
        signUpEmail.setText(message);

        emailSender.send(signUpEmail);
    }

    @Override
    public void sendForgotPasswordEmail(String to) {
        User user = userRepository.findUserByEmail(to);
        if (user != null) {
            try {
                MimeMessage mimeMessage = emailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

                messageHelper.setFrom("donotreply@ever.com");
                messageHelper.setTo(to);
                messageHelper.setSubject("Reset Password");

                String resetToken = UUID.randomUUID().toString().replace("-", "");
                String resetLink = "http://localhost:4200/reset-password?token=" + resetToken;

                user.setResetPwdToken(resetToken);
                user.setResetPwdTokenExpDt(LocalDateTime.now().plusMinutes(30));
                userRepository.save(user);

                Context context = new Context();
                context.setVariable("resetLink", resetLink);

                String htmlContent = templateEngine.process("forgot-pwd-email", context);
                messageHelper.setText(htmlContent, true);

                emailSender.send(mimeMessage);

            } catch (Exception e) {
                logger.error("Error sending reset password email", e);
            }
        } else {
            logger.info("The email you entered: {}, does not exist in our system.", to);
        }
    }
}
