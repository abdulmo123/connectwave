package com.abdulmo123.connectwave.controller;

import com.abdulmo123.connectwave.dto.PostDto;
import com.abdulmo123.connectwave.entity.Post;
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

    @PostMapping("/createUserPost/{userId}")
    public ResponseEntity<Post> createUserPost(@PathVariable("userId") Long userId, @RequestBody Post post) {
        Post newPost = postService.createUserPost(userId, post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> allPosts = postService.getAllPosts();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @GetMapping("/getAllPostsDtos")
    public ResponseEntity<List<PostDto>> getAllPostDtos() {
        List<PostDto> allPosts = postService.getAllPostDtos();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }
}
