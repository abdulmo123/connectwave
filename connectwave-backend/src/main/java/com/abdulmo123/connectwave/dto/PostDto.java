package com.abdulmo123.connectwave.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class PostDto implements Serializable {
    private final Long id;
    private final String content;
    private final Date createdDate;
    private final UserDto user;
}