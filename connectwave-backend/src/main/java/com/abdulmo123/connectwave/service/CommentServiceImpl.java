package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.entity.Comment;
import com.abdulmo123.connectwave.entity.Post;
import com.abdulmo123.connectwave.entity.User;
import com.abdulmo123.connectwave.repository.CommentRepository;
import com.abdulmo123.connectwave.repository.PostRepository;
import com.abdulmo123.connectwave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getAllCommentsByUser(Long userId) {
        return commentRepository.getAllCommentsByUser(userId);
    }

    @Override
    public Comment addCommentToPost(Comment comment, Long userId, Long postId) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                Optional<Post> optionalPost = postRepository.findById(postId);
                if (optionalPost.isPresent()) {
                    Post post = optionalPost.get();
                    comment.setPost(post);
                    comment.setUser(user);
                    String publisherName = user.getFirstName() + " " + user.getLastName();
                    comment.setPublisherName(publisherName);

                    commentRepository.addCommentToPost(comment.getContent(), userId, postId);

                    // grab the newly created comment and return that
                    return commentRepository.getCommentCreated();
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("User is not found " + userId + " OR Post is not found " + postId + "!");
        }
        return null;
    }

    @Override
    public int numCommentsForPost(Long postId) {
        return commentRepository.numCommentsForPost(postId);
    }
}
