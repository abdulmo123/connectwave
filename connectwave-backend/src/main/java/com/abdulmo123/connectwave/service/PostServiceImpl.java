package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.dto.CommentDto;
import com.abdulmo123.connectwave.dto.PostDto;
import com.abdulmo123.connectwave.dto.UserDto;
import com.abdulmo123.connectwave.entity.Comment;
import com.abdulmo123.connectwave.entity.Post;
import com.abdulmo123.connectwave.entity.User;
import com.abdulmo123.connectwave.exception.UserNotFoundException;
import com.abdulmo123.connectwave.repository.CommentRepository;
import com.abdulmo123.connectwave.repository.LikeRepository;
import com.abdulmo123.connectwave.repository.PostRepository;
import com.abdulmo123.connectwave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public Post createUserPost(Long userId, Post post) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String content = post.getContent();
            post.setContent(content);
            post.setCreatedDate(Date.valueOf(LocalDate.now()));
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

    @Override
    public List<PostDto> getAllPostDtos() {
        List<Object[]> results = postRepository.getPostsAndUsers();
        List<PostDto> allPostsInfo = new ArrayList<>();

        for (Object[] result : results) {
            Post post = (Post) result[0];
            User user = (User) result[1];

            List<Comment> comments = commentRepository.getAllCommentsForPost(post.getId());

            int numLikes = likeRepository.numLikesForPost(post.getId());
            int numComments = commentRepository.numCommentsForPost(post.getId());

            List<CommentDto> commentDtos = comments.stream()
                    .map(comment -> new CommentDto(
                            comment.getId(),
                            comment.getContent(),
                            comment.getCreatedDate(),
                            new UserDto(
                                    comment.getUser().getId(),
                                    comment.getUser().getEmail(),
                                    comment.getUser().getFirstName(),
                                    comment.getUser().getLastName(),
                                    comment.getUser().getGender(),
                                    comment.getUser().getBio()
                            )
                    ))
                    .collect(Collectors.toList());

            UserDto userDto = new UserDto(
                    user.getId(), user.getEmail(), user.getFirstName(),
                    user.getLastName(), user.getGender(), user.getBio()
            );

            PostDto postDto = new PostDto(
                    post.getId(), post.getContent(),
                    post.getCreatedDate(), userDto, commentDtos, numLikes, numComments
            );

            allPostsInfo.add(postDto);
        }

        return allPostsInfo;
    }

    @Override
    public List<Post> getAllPostsByUser(Long userId) {
        return postRepository.getAllPostsByUser(userId);
    }
}
