package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.model.entity.Comment;
import java.util.List;

public interface CommentService {

    List<Comment> getAllCommentsByUser (Long userId);

    Comment addCommentToPost(Comment comment, Long userId, Long postId);

}
