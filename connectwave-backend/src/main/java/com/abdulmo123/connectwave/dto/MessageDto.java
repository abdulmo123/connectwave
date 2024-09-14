package com.abdulmo123.connectwave.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class MessageDto implements Serializable {
    private Long id;
    private Long conversationId;
    private UserDto sender;
    private UserDto receiver;
    private String message;
    private Date createdDate;

    public MessageDto() {}

    public MessageDto(Long id, Long conversationId, UserDto sender, UserDto receiver, String message, Date createdDate) {
        this.id = id;
        this.conversationId = conversationId;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.createdDate = createdDate;
    }
}
