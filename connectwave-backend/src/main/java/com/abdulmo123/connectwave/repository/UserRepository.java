package com.abdulmo123.connectwave.repository;

import com.abdulmo123.connectwave.dto.UserDto;
import com.abdulmo123.connectwave.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Optional<User> findUserById(Long id);

    @Query("SELECT new com.abdulmo123.connectwave.dto.UserDto " +
            "(u.id, u.email, u.password, " +
            "u.firstName, u.lastName, u.gender, u.bio, " +
            "u.createdDate, u.lastLoginDate) " +
            "FROM User u " +
            "JOIN u.userPosts p " +
            "WHERE p.id = :postId")
    UserDto findUserByPostId(@Param("postId") Long postId);
}
