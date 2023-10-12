package com.abdulmo123.connectwave.controller;

import com.abdulmo123.connectwave.entity.AuthRequest;
import com.abdulmo123.connectwave.entity.AuthResponse;
import com.abdulmo123.connectwave.service.JwtTokenService;
import com.abdulmo123.connectwave.service.JwtUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtUserDetailsService jwtUserDetailsService;
    private JwtTokenService jwtTokenService;

    @PostMapping("/auth")
    public AuthResponse authenticate(@RequestBody @Valid final AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword()
            ));
        }

        catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authRequest.getUsername());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(jwtTokenService.generateToken(userDetails));

        return authResponse;
    }
}
