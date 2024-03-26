package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.entity.Post;
import com.abdulmo123.connectwave.repository.LikeRepository;
import com.abdulmo123.connectwave.repository.PostRepository;
import com.abdulmo123.connectwave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private final LikeRepository likeRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PostRepository postRepository;

    public LikeService(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<Post> getAllLikesByUser (Long userId) {
        return postRepository.getPostsLikedByUser(userId);
    }

    public void postLikeUnlike(Long userId, Long postId, String isLiked) {
//        likeRepository.unlikePost(userId, postId);
        // TODO: if post is liked, add it to the like table

        if (isLiked.equals("Y")) {
            postRepository.likePost(userId, postId);
        }
        else if (isLiked.equals("N")){
            postRepository.unlikePost(userId, postId);
        }
        else {
            ;
            // TODO: exception handling
//            return new LikeNotFoundException("User with id: " + userId + " not found OR Post with id: " + postId + " not found!");
        }
        // TODO: if post is unliked, delete it from the like table
    }
}
