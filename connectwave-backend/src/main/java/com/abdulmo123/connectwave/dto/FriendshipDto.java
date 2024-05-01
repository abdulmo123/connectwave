package com.abdulmo123.connectwave.dto;

import com.abdulmo123.connectwave.enums.FriendshipStatus;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class FriendshipDto implements Serializable {
    private Long id;
    private UserDto user;
    private UserDto friend;
    private FriendshipStatus status;
    private Date createdDate;

    public FriendshipDto() {};

    public FriendshipDto(Long id, UserDto user, UserDto friend, FriendshipStatus status, Date createdDate) {
        this.id = id;
        this.user = user;
        this.friend = friend;
        this.status = status;
        this.createdDate = createdDate;
    }
}
