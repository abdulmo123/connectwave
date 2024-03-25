package com.abdulmo123.connectwave.repository;

import com.abdulmo123.connectwave.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query(value = "select * from connectwave.blog order by created_date desc", nativeQuery = true)
    List<Blog> getAllBlogPosts();

}
