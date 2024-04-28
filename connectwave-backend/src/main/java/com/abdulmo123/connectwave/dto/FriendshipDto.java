package com.abdulmo123.connectwave.dto;

import com.abdulmo123.connectwave.enums.FriendshipStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class FriendshipDto implements Serializable {
    private Long id;
    private UserDto user;
    private UserDto friend;
    private FriendshipStatus status;

    public FriendshipDto() {};

    public FriendshipDto(Long id, UserDto user, UserDto friend, FriendshipStatus status) {
        this.id = id;
        this.user = user;
        this.friend = friend;
        this.status = status;
    }
}
