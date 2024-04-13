package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.entity.Post;

import java.util.List;

public interface PostService {

    Post createUserPost(Long userId, Post post);

    List<Post> getAllPosts();
}
