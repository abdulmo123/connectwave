package com.abdulmo123.connectwave.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class UserDto implements Serializable {
    private final Long id;
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String bio;
    private final Date createdDate;
    private final Date lastLoginDate;
}