package com.abdulmo123.connectwave.controller;

import com.abdulmo123.connectwave.model.entity.Comment;
import com.abdulmo123.connectwave.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{userId}/getAllCommentsByUser")
    public ResponseEntity<List<Comment>> getAllCommentsByUser(@PathVariable("userId") Long userId) {
        List<Comment> allCommentsByUser = commentService.getAllCommentsByUser(userId);
        return new ResponseEntity<>(allCommentsByUser, HttpStatus.OK);
    }

    @PostMapping("/{userId}/{postId}/addCommentToPost")
    public ResponseEntity<Comment> addCommentToPost(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId, @RequestBody Comment comment) {
        Comment newCommentToPost = commentService.addCommentToPost(comment, userId, postId);
        return new ResponseEntity<>(newCommentToPost, HttpStatus.CREATED);
    }
}
