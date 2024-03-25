package com.abdulmo123.connectwave.controller;

import com.abdulmo123.connectwave.entity.Blog;
import com.abdulmo123.connectwave.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/createUserBlogPost/{userId}")
    public ResponseEntity<Blog> createUserBlogPost(@PathVariable("userId") Long userId, @RequestBody Blog blog) {
        Blog newBlog = blogService.createUserBlogPost(userId, blog);
        return new ResponseEntity<>(newBlog, HttpStatus.CREATED);
    }

    @GetMapping("/getAllBlogPosts")
    public ResponseEntity<List<Blog>> getAllBlogPosts() {
        List<Blog> allBlogPosts = blogService.getAllBlogPosts();
        return new ResponseEntity<>(allBlogPosts, HttpStatus.OK);
    }
}
