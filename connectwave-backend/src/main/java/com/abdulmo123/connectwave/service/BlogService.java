package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.entity.Blog;
import com.abdulmo123.connectwave.entity.User;
import com.abdulmo123.connectwave.exception.UserNotFoundException;
import com.abdulmo123.connectwave.repository.BlogRepository;
import com.abdulmo123.connectwave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private final BlogRepository blogRepository;

    @Autowired
    private final UserRepository userRepository;

    public BlogService(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    public Blog createUserBlogPost(Long userId, Blog blog) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String content = blog.getContent();
            blog.setContent(content);
            blog.setCreatedDate(new Date());
            blog.setUser(user);

            String publisherName = user.getFirstName() + " " + user.getLastName();
            blog.setPublisherName(publisherName);

            user.getUserBlogPosts().add(blog);

            return blogRepository.save(blog);
        }
        else {
            throw new UserNotFoundException("User with id: " + userId + " not found!");
        }
    }

    public List<Blog> getAllBlogPosts() {
        return blogRepository.getAllBlogPosts();
    }
}
