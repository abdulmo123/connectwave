package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.entity.Post;
import com.abdulmo123.connectwave.entity.User;
import com.abdulmo123.connectwave.exception.UserNotFoundException;
import com.abdulmo123.connectwave.repository.PostRepository;
import com.abdulmo123.connectwave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    /*public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }*/

    @Override
    public Post createUserPost(Long userId, Post post) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String content = post.getContent();
            post.setContent(content);
            post.setCreatedDate(new Date());
            post.setUser(user);

            String publisherName = user.getFirstName() + " " + user.getLastName();
            post.setPublisherName(publisherName);

            user.getUserPosts().add(post);

            return postRepository.save(post);
        }
        else {
            throw new UserNotFoundException("User with id: " + userId + " not found!");
        }
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.getAllPosts();
    }
}
