package com.abdulmo123.connectwave.controller;

import com.abdulmo123.connectwave.dto.FriendshipDto;
import com.abdulmo123.connectwave.entity.Friendship;
import com.abdulmo123.connectwave.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friendships")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    @GetMapping("/getUserFriendships/{userId}")
    private ResponseEntity<List<FriendshipDto>> getUserFriendship(@PathVariable("userId") Long userId) {
        List<FriendshipDto> userFriendships = friendshipService.getUserFriendships(userId);
        return new ResponseEntity<>(userFriendships, HttpStatus.OK);
    }

    @PostMapping("/sendFriendshipRequest/{userId}/{friendId}")
    private ResponseEntity<FriendshipDto> sendFriendshipRequest(@PathVariable("userId") Long userId, @PathVariable("friendId") Long friendId) {
        FriendshipDto newFriendshipRequest = friendshipService.sendFriendshipRequest(userId, friendId);
        return new ResponseEntity<>(newFriendshipRequest, HttpStatus.CREATED);
    }

    @GetMapping("/existingFriendshipRequest/{userId}/{friendId}")
    private ResponseEntity<List<Friendship>> existingFriendshipRequest(@PathVariable("userId") Long userId, @PathVariable("friendId") Long friendId) {
        List<Friendship> existingFriendshipRequest = friendshipService.existingFriendshipRequest(userId, friendId);
        return new ResponseEntity<>(existingFriendshipRequest, HttpStatus.OK);
    }

    @PostMapping("/respondToFriendshipRequest/{action}/{userId}/{friendId}")
    private ResponseEntity<Friendship> responseToFriendshipRequest(@PathVariable("action") String action,
                                                                   @PathVariable("userId") Long userId,
                                                                   @PathVariable("friendId") Long friendId) {

        Friendship responseToFriendshipRequest = friendshipService.respondToFriendshipRequest(userId, friendId, action);
        return new ResponseEntity<>(responseToFriendshipRequest, HttpStatus.OK);
    }

    @DeleteMapping("/removeExistingFriend/{userId}/{friendId}")
    private void removeExistingFriend(@PathVariable("userId") Long userId, @PathVariable("friendId") Long friendId) {
        friendshipService.removeExistingFriendship(userId, friendId);
    }
}
