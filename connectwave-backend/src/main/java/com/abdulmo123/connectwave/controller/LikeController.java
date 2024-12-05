package com.abdulmo123.connectwave.controller;

import com.abdulmo123.connectwave.model.entity.Post;
import com.abdulmo123.connectwave.model.response.HttpResponse;
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

    @GetMapping("/{userId}/getAllLikesByUser")
    public ResponseEntity<HttpResponse> getAllLikesByUser(@PathVariable ("userId") Long userId) {
        HttpResponse allLikesByUser = likeService.getAllLikesByUser(userId);
        return new ResponseEntity<>(allLikesByUser, HttpStatus.OK);
    }

    @PostMapping("/{userId}/{postId}/{isLiked}/saveLikeStatus")
    public ResponseEntity<Post> saveLikeStatus(@PathVariable ("userId") Long userId, @PathVariable("postId") Long postId, @PathVariable("isLiked") String isLiked) throws Exception {
        try {
            Post post = likeService.saveLikeStatus(userId, postId, isLiked);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }

        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
