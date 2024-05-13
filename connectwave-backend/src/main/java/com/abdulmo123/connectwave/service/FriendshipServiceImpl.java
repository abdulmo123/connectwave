package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.dto.FriendshipDto;
import com.abdulmo123.connectwave.dto.UserDto;
import com.abdulmo123.connectwave.entity.Friendship;
import com.abdulmo123.connectwave.entity.User;
import com.abdulmo123.connectwave.enums.FriendshipStatus;
import com.abdulmo123.connectwave.repository.FriendshipRepository;
import com.abdulmo123.connectwave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    /*@Override
    public List<FriendshipDto> getUserFriendships(Long senderId) {
        List<Object[]> results = friendshipRepository.getFriendshipList(senderId);
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
            FriendshipDto friendshipDto = new FriendshipDto();
            if (Objects.equals(user.getId(), senderId)) {
                friendshipDto.setId(id);
                friendshipDto.setReceiver(friendDto);
                friendshipDto.setStatus(status);
                friendshipDto.setCreatedDate(createdDate);
            }

            else if (Objects.equals(friend.getId(), senderId)) {
                friendshipDto.setId(id);
                friendshipDto.setSender(userDto);
                friendshipDto.setStatus(status);
                friendshipDto.setCreatedDate(createdDate);
            }

//            FriendshipDto friendshipDto = new FriendshipDto(id, userDto, friendDto, status, createdDate);
            allFriendshipsInfo.add(friendshipDto);
        }

        return allFriendshipsInfo;
    }*/

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
        List<Friendship> existingFriendship = friendshipRepository.existingFriendship(senderId, receiverId);
        friendshipRepository.removeExistingFriendship(senderId, receiverId);
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

    /*@Override
    public List<FriendshipDto> getPendingSentFriendshipRequests(Long senderId) {
        List<Object[]> results = friendshipRepository.getPendingSentFriendshipRequests(senderId);
        List<FriendshipDto> allPendingSentFriendshipRequests = new ArrayList<>();

        for (Object [] obj : results) {
            Long id = (Long) obj[0];
            User friend = (User) obj[2];
            FriendshipStatus status = (FriendshipStatus) obj[3];
            Date createdDate = (Date) obj[4];

            UserDto friendDto = new UserDto(
                    friend.getId(), friend.getEmail(), friend.getFirstName(),
                    friend.getLastName(), friend.getGender(), friend.getBio()
            );

            FriendshipDto friendshipDto = new FriendshipDto(id, null, friendDto, status, createdDate);
            allPendingSentFriendshipRequests.add(friendshipDto);
        }

        return allPendingSentFriendshipRequests;
    }

    @Override
    public List<FriendshipDto> getPendingReceivedFriendRequests(Long receiverId) {
        List<Object[]> results = friendshipRepository.getPendingReceivedFriendshipRequests(receiverId);
        List<FriendshipDto> allPendingReceivedFriendshipRequests = new ArrayList<>();

        for (Object [] obj : results) {
            Long id = (Long) obj[0];
            User user = (User) obj[1];
            FriendshipStatus status = (FriendshipStatus) obj[3];
            Date createdDate = (Date) obj[4];

            UserDto userDto = new UserDto(
                    user.getId(), user.getEmail(), user.getFirstName(),
                    user.getLastName(), user.getGender(), user.getBio()
            );

            FriendshipDto friendshipDto = new FriendshipDto(id, userDto, null, status, createdDate);
            allPendingReceivedFriendshipRequests.add(friendshipDto);
        }

        return allPendingReceivedFriendshipRequests;
    }*/
}
