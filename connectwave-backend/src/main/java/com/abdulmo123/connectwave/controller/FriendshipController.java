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

    /*@GetMapping("/getUserFriendships/{senderId}")
    private ResponseEntity<List<FriendshipDto>> getUserFriendship(@PathVariable("senderId") Long senderId) {
        List<FriendshipDto> userFriendships = friendshipService.getUserFriendships(senderId);
        return new ResponseEntity<>(userFriendships, HttpStatus.OK);
    }*/

    @PostMapping("/{senderId}/{receiverId}/sendFriendshipRequest")
    private ResponseEntity<FriendshipDto> sendFriendshipRequest(@PathVariable("senderId") Long senderId, @PathVariable("receiverId") Long receiverId) {
        FriendshipDto newFriendshipRequest = friendshipService.sendFriendshipRequest(senderId, receiverId);
        return new ResponseEntity<>(newFriendshipRequest, HttpStatus.CREATED);
    }

    @GetMapping("/{senderId}/{receiverId}/existingFriendshipRequest")
    private ResponseEntity<Friendship> existingFriendshipRequest(@PathVariable("senderId") Long senderId, @PathVariable("receiverId") Long receiverId) {
        Friendship existingFriendshipRequest = friendshipService.existingFriendshipRequest(senderId, receiverId);
        return new ResponseEntity<>(existingFriendshipRequest, HttpStatus.OK);
    }

    @PostMapping("/{senderId}/{receiverId}/{action}/respondToFriendshipRequest")
    private ResponseEntity<Friendship> responseToFriendshipRequest(@PathVariable("action") String action,
                                                                   @PathVariable("senderId") Long senderId,
                                                                   @PathVariable("receiverId") Long receiverId) {

        Friendship responseToFriendshipRequest = friendshipService.respondToFriendshipRequest(senderId, receiverId, action);
        return new ResponseEntity<>(responseToFriendshipRequest, HttpStatus.OK);
    }

    @DeleteMapping("/{senderId}/{receiverId}/removeExistingFriend")
    private void removeExistingFriend(@PathVariable("senderId") Long senderId, @PathVariable("receiverId") Long receiverId) {
        friendshipService.removeExistingFriendship(senderId, receiverId);
    }

    /*@GetMapping("/{senderId}/getPendingSentFriendshipRequests")
    public ResponseEntity<List<FriendshipDto>> getPendingSentFriendshipRequests(@PathVariable("senderId") Long senderId) {
        List<FriendshipDto> allPendingSentFriendshipRequests = friendshipService.getPendingSentFriendshipRequests(senderId);
        return new ResponseEntity<>(allPendingSentFriendshipRequests, HttpStatus.OK);
    }

    @GetMapping("/{receiverId}/getPendingReceivedFriendshipRequests")
    public ResponseEntity<List<FriendshipDto>> getPendingReceivedFriendshipRequests(@PathVariable("receiverId") Long receiverId) {
        List<FriendshipDto> allPendingReceivedFriendshipRequests = friendshipService.getPendingReceivedFriendRequests(receiverId);
        return new ResponseEntity<>(allPendingReceivedFriendshipRequests, HttpStatus.OK);
    }*/
}
