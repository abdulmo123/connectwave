package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.entity.Post;
import com.abdulmo123.connectwave.repository.LikeRepository;
import com.abdulmo123.connectwave.repository.PostRepository;
import com.abdulmo123.connectwave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LikeService {

    List<Post> getAllLikesByUser (Long userId);

    Post saveLikeStatus(Long userId, Long postId, String isLiked) throws Exception;

//    int numLikesForPost(Long postId);
}
