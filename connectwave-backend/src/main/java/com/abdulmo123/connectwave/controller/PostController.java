package com.abdulmo123.connectwave.controller;

import com.abdulmo123.connectwave.dto.PostDto;
import com.abdulmo123.connectwave.model.entity.Post;
import com.abdulmo123.connectwave.model.response.HttpResponse;
import com.abdulmo123.connectwave.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/{userId}/createUserPost")
    public ResponseEntity<HttpResponse> createUserPost(@PathVariable("userId") Long userId, @RequestBody Post post) {
        HttpResponse newPost = postService.createUserPost(userId, post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<HttpResponse> getAllPosts() {
        HttpResponse allPosts = postService.getAllPosts();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @GetMapping("/getAllPostsInfo")
    public ResponseEntity<HttpResponse> getAllPostDtos() {
        HttpResponse allPosts = postService.getAllPostDtos();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @GetMapping("/{userId}/getUserPosts")
    public ResponseEntity<HttpResponse> getAllPostsByUser(@PathVariable("userId") Long userId) {
        HttpResponse allPostsByUser = postService.getAllPostsByUser(userId);
        return new ResponseEntity<>(allPostsByUser, HttpStatus.OK);
    }
}
