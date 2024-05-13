package com.abdulmo123.connectwave.controller;

import com.abdulmo123.connectwave.dto.UserDto;
import com.abdulmo123.connectwave.entity.User;
import com.abdulmo123.connectwave.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findUserByPostId/{postId}")
    public ResponseEntity<UserDto> findUserByPostId(@PathVariable("postId") Long postId) {
        UserDto user = userService.findUserByPostId(postId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{userId}/getUserFriendships")
    public ResponseEntity<List<User>> getUserFriendships(@PathVariable("userId") Long userId) {
        List<User> userFriendships = userService.getUserFriendships(userId);
        return new ResponseEntity<>(userFriendships, HttpStatus.OK);
    }

    @GetMapping("/{userId}/getPendingSentFriendshipRequests")
    public ResponseEntity<List<UserDto>> getPendingSentFriendshipRequests(@PathVariable("userId") Long userId) {
        List<UserDto> pendingSentFriendshipRequests = userService.getPendingSentFriendshipRequests(userId);
        return new ResponseEntity<>(pendingSentFriendshipRequests, HttpStatus.OK);
    }

    @GetMapping("/{userId}/getPendingReceivedFriendshipRequests")
    public ResponseEntity<List<UserDto>> getPendingReceivedFriendshipRequests(@PathVariable("userId") Long userId) {
        List<UserDto> pendingReceivedFriendshipRequests = userService.getPendingReceivedFriendshipRequests(userId);
        return new ResponseEntity<>(pendingReceivedFriendshipRequests, HttpStatus.OK);
    }
}
