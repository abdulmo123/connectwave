package com.abdulmo123.connectwave.repository;

import com.abdulmo123.connectwave.dto.PostDto;
import com.abdulmo123.connectwave.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select * from connectwave.post order by created_date desc", nativeQuery = true)
    List<Post> getAllPosts();

    @Query(value = "select p.* from connectwave.post p join connectwave.likes l on p.id = l.post_id where l.user_id = :userId", nativeQuery = true)
    List<Post> getPostsLikedByUser(@Param("userId") Long userId);

    @Modifying
    @Query(value = "delete from connectwave.likes where likes.user_id = :userId and likes.post_id = :postId", nativeQuery = true)
    @Transactional
    void unlikePost(@Param("userId") Long userId, @Param("postId") Long postId);

    @Modifying
    @Query(value = "insert into connectwave.likes (user_id, post_id, created_date) " +
            "values (:userId, :postId, current_timestamp) " +
            "on duplicate key update created_date = current_timestamp", nativeQuery = true)
    @Transactional
    void likePost(@Param("userId") Long userId, @Param("postId") Long postId);

    @Query("SELECT new com.abdulmo123.connectwave.dto.PostDto(" +
            "p.id, p.content, p.createdDate, " +
            "new com.abdulmo123.connectwave.dto.UserDto(u.id, u.email, u.password, u.firstName, u.lastName, u.gender, u.bio, u.createdDate, u.lastLoginDate)) " +
            "FROM Post p LEFT JOIN p.user u " +
            "ORDER BY p.createdDate DESC")
    List<PostDto> getAllPostDtos();
}
