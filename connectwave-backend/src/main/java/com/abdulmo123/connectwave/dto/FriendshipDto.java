package com.abdulmo123.connectwave.dto;

import com.abdulmo123.connectwave.enums.FriendshipStatus;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class FriendshipDto implements Serializable {
    private Long id;
    private UserDto sender;
    private UserDto receiver;
    private FriendshipStatus status;
    private Date createdDate;

    public FriendshipDto() {};

    public FriendshipDto(Long id, UserDto sender, UserDto receiver, FriendshipStatus status, Date createdDate) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
        this.createdDate = createdDate;
    }

    public FriendshipDto(Long id, UserDto receiver, FriendshipStatus status, Date createdDate) {
        this.id = id;
        this.receiver = receiver;
        this.status = status;
        this.createdDate = createdDate;
    }
}
