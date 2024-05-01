package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.dto.FriendshipDto;
import com.abdulmo123.connectwave.entity.Friendship;

import java.util.List;

public interface FriendshipService {

    List<FriendshipDto> getUserFriendships(Long userId);

    FriendshipDto sendFriendshipRequest(Long userId, Long friendId);

    List<Friendship> existingFriendshipRequest(Long userId, Long friendId);

    Friendship respondToFriendshipRequest(Long userId, Long friendId, String action);

    void removeExistingFriendship(Long userId, Long friendId);

    FriendshipDto getNewFriendshipRequest(Long userId, Long friendId);
}
