package com.abdulmo123.connectwave.repository;

import com.abdulmo123.connectwave.model.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query(value = "select count(*) from connectwave.likes l where l.post_id = :postId" , nativeQuery = true)
    int numLikesForPost(@Param("postId") Long postId);
}
