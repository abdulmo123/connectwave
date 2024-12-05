package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.dto.PostDto;
import com.abdulmo123.connectwave.model.entity.Post;
import com.abdulmo123.connectwave.model.response.HttpResponse;

import java.util.List;

public interface PostService {

    HttpResponse createUserPost(Long userId, Post post);

    HttpResponse getAllPosts();

    HttpResponse getAllPostDtos();

    HttpResponse getAllPostsByUser(Long userId);
}
