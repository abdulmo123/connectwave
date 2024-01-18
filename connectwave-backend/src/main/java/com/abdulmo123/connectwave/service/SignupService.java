package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.entity.AppUser;
import com.abdulmo123.connectwave.entity.AppUserRole;
import com.abdulmo123.connectwave.entity.SignupRequest;
import com.abdulmo123.connectwave.entity.User;
import com.abdulmo123.connectwave.util.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    public SignupService(UserService userService, EmailValidator emailValidator) {
        this.userService = userService;
        this.emailValidator = emailValidator;
    }

    public String signup(SignupRequest signupRequest) {
        boolean isValidEmail = emailValidator.
                test(signupRequest.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid!:");
        }
        return userService.signUpUser(
                new AppUser (
                        signupRequest.getFirstName(),
                        signupRequest.getLastName(),
                        signupRequest.getEmail(),
                        signupRequest.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
