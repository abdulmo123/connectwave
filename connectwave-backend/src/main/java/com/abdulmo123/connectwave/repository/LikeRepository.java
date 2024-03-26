package com.abdulmo123.connectwave.repository;

import com.abdulmo123.connectwave.entity.Like;
import com.abdulmo123.connectwave.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    /*@Query(value = "select * from connectwave.likes where likes.post_id = :postId", nativeQuery = true)
    List<Like> getAllLikesForPost(@Param("postId") Long postId);*/

    /*@Query(value = "SELECT * FROM connectwave.likes WHERE likes.user_id = :userId", nativeQuery = true)
    List<Like> getAllLikesByUser(@Param("userId") Long userId);*/

    /*@Query(value = "DELETE FROM connectwave.likes WHERE likes.user_id = :userId AND likes.post_id = :postId", nativeQuery = true)
    Post unlikePost(@Param("userId") Long userId, @Param("postId") Long postId);

    @Query(value = "INSERT into connectwave.likes (likes.user_id, likes.post_id) VALUES (:userId, :postId)", nativeQuery = true)
    Post likePost(@Param("userId") Long userId, @Param("postId") Long postId);*/

}
