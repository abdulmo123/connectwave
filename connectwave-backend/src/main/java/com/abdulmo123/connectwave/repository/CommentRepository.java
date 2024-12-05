package com.abdulmo123.connectwave.repository;

import com.abdulmo123.connectwave.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select c.* from connectwave.comment c where c.user_id = :userId", nativeQuery = true)
    List<Comment> getAllCommentsByUser(@Param("userId") Long userId);

    @Modifying
    @Query(value = "insert into connectwave.comment (content, created_date, post_id, user_id) " +
            "values (:content, current_timestamp, :postId, :userId)", nativeQuery = true)
    @Transactional
    void addCommentToPost(@Param("content") String content, @Param("userId") Long userId, @Param("postId") Long postId);

    @Query(value = "select count(*) from connectwave.comment c where c.post_id = :postId", nativeQuery = true)
    int numCommentsForPost(@Param("postId") Long postId);

    @Query(value = "select c.* from connectwave.comment c where c.post_id = :postId", nativeQuery = true)
    List<Comment> getAllCommentsForPost(@Param("postId") Long postId);

    @Query(value = "select c.* from connectwave.comment c order by created_date desc limit 1", nativeQuery = true)
    Comment getCommentCreated();
}
