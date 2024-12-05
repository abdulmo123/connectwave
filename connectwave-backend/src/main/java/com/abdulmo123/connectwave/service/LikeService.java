package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.model.entity.Post;
import com.abdulmo123.connectwave.model.response.HttpResponse;

import java.util.List;

public interface LikeService {

    HttpResponse getAllLikesByUser (Long userId);

    Post saveLikeStatus(Long userId, Long postId, String isLiked) throws Exception;

}
