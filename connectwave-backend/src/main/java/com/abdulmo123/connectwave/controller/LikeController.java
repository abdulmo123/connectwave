package com.abdulmo123.connectwave.controller;

import com.abdulmo123.connectwave.entity.Like;
import com.abdulmo123.connectwave.entity.Post;
import com.abdulmo123.connectwave.service.LikeService;
import com.abdulmo123.connectwave.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private PostService postService;

    @GetMapping("/getAllLikesByUser/{userId}")
    public ResponseEntity<List<Post>> getAllLikesByUser(@PathVariable ("userId") Long userId) {
        List<Post> allLikesByUser = likeService.getAllLikesByUser(userId);
        return new ResponseEntity<>(allLikesByUser, HttpStatus.OK);
    }

    @PostMapping("/unlikePost/{userId}/{postId}/{isLiked}")
    public void unlikePost(@PathVariable ("userId") Long userId, @PathVariable("postId") Long postId, @PathVariable("isLiked") String isLiked) {
//        Post post = likeService.postLikeUnlike(userId, postId, isLiked);
//        return new ResponseEntity<>(post, HttpStatus.OK);
        likeService.postLikeUnlike(userId, postId, isLiked);
    }
}
