package com.abdulmo123.connectwave.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

@Service
public class JwtTokenService {
    private static final Duration JWT_TOKEN_VALID_TIME = Duration.ofMinutes(20);
//    private Logger log = (Logger) LoggerFactory.getLogger(JwtTokenService.class);

    private Algorithm hmac512;
    private JWTVerifier jwtVerifier;

    public JwtTokenService(@Value("${jwt.secret}") String secret) {
        this.hmac512 = Algorithm.HMAC512(secret);
        this.jwtVerifier = JWT.require(this.hmac512).build();
    }

    public String generateToken(UserDetails userDetails) {
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer("app")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALID_TIME.toMillis()))
                .sign(this.hmac512);
    }

    public String validateTokenAndGetUsername(String token) {
        try {
            return jwtVerifier.verify(token).getSubject();
        }
        catch (JWTVerificationException jwtVerificationException) {
//            log.warn("token invalid: {}", jwtVerificationException.getMessage());
            System.out.println("token invalid: {}" + jwtVerificationException.getMessage());
            return null;
        }
    }
}
