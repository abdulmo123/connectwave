package com.abdulmo123.connectwave.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String bio;

    public UserDto(Long id, String email, String firstName, String lastName, String gender, String bio) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.bio = bio;
    }
}