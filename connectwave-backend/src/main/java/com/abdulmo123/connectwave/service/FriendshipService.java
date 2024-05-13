package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.dto.FriendshipDto;
import com.abdulmo123.connectwave.entity.Friendship;

import java.util.List;

public interface FriendshipService {

    FriendshipDto sendFriendshipRequest(Long senderId, Long receiverId);

    Friendship existingFriendshipRequest(Long senderId, Long receiverId);

    void cancelSentFriendshipRequest(Long senderId, Long receiverId);

    Friendship respondToFriendshipRequest(Long senderId, Long receiverId, String action);

    void removeExistingFriendship(Long senderId, Long receiverId);

    FriendshipDto getNewFriendshipRequest(Long senderId, Long receiverId);

}
