package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.dto.FriendshipDto;
import com.abdulmo123.connectwave.dto.UserDto;
import com.abdulmo123.connectwave.model.entity.Friendship;
import com.abdulmo123.connectwave.model.entity.User;
import com.abdulmo123.connectwave.enums.FriendshipStatus;
import com.abdulmo123.connectwave.repository.FriendshipRepository;
import com.abdulmo123.connectwave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public FriendshipDto sendFriendshipRequest(Long senderId, Long receiverId) {
        friendshipRepository.sendFriendshipRequest(senderId, receiverId);
        return getNewFriendshipRequest(senderId, receiverId);
    }

    @Override
    public Friendship existingFriendshipRequest(Long senderId, Long receiverId) {
        return friendshipRepository.existingFriendshipRequest(senderId, receiverId);
    }

    @Override
    public void cancelSentFriendshipRequest(Long senderId, Long receiverId) {
        Friendship existingFriendshipRequest = friendshipRepository.existingFriendshipRequest(senderId, receiverId);

        if (existingFriendshipRequest != null) {
            friendshipRepository.cancelSentFriendshipRequest(senderId, receiverId);
        }
    }

    @Override
    public Friendship respondToFriendshipRequest(Long senderId, Long receiverId, String action) {
        // person receiving request responds to it, sender CAN'T respond to friend request they sent
        Friendship existingFriendshipRequest = friendshipRepository.existingFriendshipRequest(senderId, receiverId);
        if (action.equals("Accept")) {
            friendshipRepository.acceptFriendshipRequest(senderId, receiverId);
        }
        else if (action.equals("Reject")){
            friendshipRepository.rejectFriendshipRequest(senderId, receiverId);
        }

        return friendshipRepository.friendshipRequestStatusAfterResponse(senderId, receiverId);
    }

    @Override
    public void removeExistingFriendship(Long senderId, Long receiverId) {
        Friendship existingFriendship = friendshipRepository.existingFriendship(senderId, receiverId);
        friendshipRepository.removeExistingFriendship(senderId, receiverId);
    }

    @Override
    public Friendship existingFriendshipRelationship(Long senderId, Long receiverId) {
        return friendshipRepository.existingFriendship(senderId, receiverId);
    }

    @Override
    public FriendshipDto getNewFriendshipRequest(Long senderId, Long receiverId) {
        List<Object[]> results = friendshipRepository.getNewFriendshipRequest(senderId, receiverId);
        List<FriendshipDto> allFriendshipsInfo = new ArrayList<>();

        for (Object [] obj : results) {
            Long id = (Long) obj[0];
            User user = (User) obj[1];
            User friend = (User) obj[2];
            FriendshipStatus status = (FriendshipStatus) obj[3];
            Date createdDate = (Date) obj[4];

            UserDto userDto = new UserDto(
                    user.getId(), user.getEmail(), user.getFirstName(),
                    user.getLastName(), user.getGender(), user.getBio()
            );

            UserDto friendDto = new UserDto(
                    friend.getId(), friend.getEmail(), friend.getFirstName(),
                    friend.getLastName(), friend.getGender(), friend.getBio()
            );

            FriendshipDto friendshipDto = new FriendshipDto(id, userDto, friendDto, status, createdDate);
            allFriendshipsInfo.add(friendshipDto);
        }

        return allFriendshipsInfo.get(0);
    }
}
