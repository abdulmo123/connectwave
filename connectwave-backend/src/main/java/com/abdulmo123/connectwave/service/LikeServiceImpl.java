package com.abdulmo123.connectwave.service;


import com.abdulmo123.connectwave.model.entity.Post;
import com.abdulmo123.connectwave.model.response.HttpResponse;
import com.abdulmo123.connectwave.repository.LikeRepository;
import com.abdulmo123.connectwave.repository.PostRepository;
import com.abdulmo123.connectwave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public HttpResponse getAllLikesByUser (Long userId) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(200);
        httpResponse.setMessage("All likes by user with id : " + userId);
        httpResponse.setData(postRepository.getPostsLikedByUser(userId));

        return httpResponse;
    }

    @Override
    public Post saveLikeStatus(Long userId, Long postId, String isLiked) throws Exception {

        try {
            if (isLiked.equals("Y")) {
                postRepository.likePost(userId, postId);
            }
            else if (isLiked.equals("N")){
                postRepository.unlikePost(userId, postId);
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Invalid value for isLiked: " + isLiked);
        }
        return null;
    }
}
