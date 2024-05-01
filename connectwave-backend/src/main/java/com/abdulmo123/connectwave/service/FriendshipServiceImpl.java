package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.dto.FriendshipDto;
import com.abdulmo123.connectwave.dto.UserDto;
import com.abdulmo123.connectwave.entity.Friendship;
import com.abdulmo123.connectwave.entity.User;
import com.abdulmo123.connectwave.enums.FriendshipStatus;
import com.abdulmo123.connectwave.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Override
    public List<FriendshipDto> getUserFriendships(Long userId) {
        List<Object[]> results = friendshipRepository.getFriendshipList(userId);
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

        return allFriendshipsInfo;
    }

    @Override
    public FriendshipDto sendFriendshipRequest(Long userId, Long friendId) {
        friendshipRepository.sendFriendshipRequest(userId, friendId);
        return getNewFriendshipRequest(userId, friendId);
    }

    @Override
    public List<Friendship> existingFriendshipRequest(Long userId, Long friendId) {
        return friendshipRepository.exisitingFriendshipRequest(userId, friendId);
    }

    @Override
    public Friendship respondToFriendshipRequest(Long userId, Long friendId, String action) {
        List<Friendship> existingFriendshipRequest = friendshipRepository.exisitingFriendshipRequest(userId, friendId);
        boolean doesExist = false;
        for (int i = 0; i < existingFriendshipRequest.size(); i++) {
            if (existingFriendshipRequest.get(i).getStatus().toString().equals("PENDING")) {
                doesExist = true;
            }
            else {
                doesExist = false;
            }
//            System.out.println("Index " + i + " : => " + existingFriendshipRequest.get(i).getStatus());
        }
        if (doesExist) {
            if (action.equals("Accept")) {
                friendshipRepository.acceptFriendshipRequest(userId, friendId);
            }
            else if (action.equals("Reject")){
                friendshipRepository.rejectFriendshipRequest(userId, friendId);
//                friendshipRepository.removeExistingFriendship(userId, friendId);
            }
        }
        return null;
    }

    @Override
    public void removeExistingFriendship(Long userId, Long friendId) {
        List<Friendship> existingFriendshipRequest = friendshipRepository.exisitingFriendshipRequest(userId, friendId);
        boolean isFriend = false;
        for (int i = 0; i < existingFriendshipRequest.size(); i++) {
            if (existingFriendshipRequest.get(i).getStatus().toString().equals("FRIEND")) {
                isFriend = true;
            }
            else {
                isFriend = false;
            }
//            System.out.println("Index " + i + " : => " + existingFriendshipRequest.get(i).getStatus());
        }

        if (isFriend) {
            friendshipRepository.removeExistingFriendship(userId, friendId);
        }
    }

    @Override
    public FriendshipDto getNewFriendshipRequest(Long userId, Long friendId) {
        List<Object[]> results = friendshipRepository.getNewFriendshipRequest(userId, friendId);
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
