package com.abdulmo123.connectwave.entity;

public class AuthResponse {

    String accessToken;

    public AuthResponse () {}

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
