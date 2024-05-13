package com.abdulmo123.connectwave.repository;

import com.abdulmo123.connectwave.dto.UserDto;
import com.abdulmo123.connectwave.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Optional<User> findUserById(Long id);

    @Query("SELECT new com.abdulmo123.connectwave.dto.UserDto " +
            "(u.id, u.email, u.password, " +
            "u.firstName, u.lastName, u.gender, u.bio) " +
            "FROM User u " +
            "JOIN u.userPosts p " +
            "WHERE p.id = :postId")
    UserDto findUserByPostId(@Param("postId") Long postId);

    // get list of user friends
    @Query(value = "SELECT u.* " +
            "FROM connectwave.user u " +
            "JOIN ( " +
            "SELECT CASE " +
            "WHEN f.sender_id = :userId THEN f.receiver_id " +
            "ELSE f.sender_id " +
            "END AS friend_id " +
            "FROM connectwave.friendship f " +
            "WHERE f.status = 'FRIEND' " +
            "AND (f.sender_id = :userId OR f.receiver_id = :userId) " +
            ") AS friends ON u.id = friends.friend_id", nativeQuery = true)
    List<User> getUserFriendships(@Param("userId") Long userId);

    // query for getting sent pending (sender) friendship requests -> status PENDING
    @Query("SELECT new com.abdulmo123.connectwave.dto.UserDto(" +
            "u.id, u.email, u.firstName, u.lastName, u.gender, u.bio), f.createdDate " +
            "FROM User u " +
            "JOIN Friendship f ON f.receiver.id = u.id " +
            "WHERE f.sender.id = :userId " +
            "AND f.status = 'PENDING'")
    List<UserDto> getPendingSentFriendshipRequests(@Param("userId") Long userId);

    // query for getting received (friend) friendship requests -> status PENDING
    @Query("SELECT new com.abdulmo123.connectwave.dto.UserDto(" +
            "u.id, u.email, u.firstName, u.lastName, u.gender, u.bio), f.createdDate " +
            "FROM User u " +
            "JOIN Friendship f ON f.sender.id = u.id " +
            "WHERE f.receiver.id = :userId " +
            "AND f.status = 'PENDING'")
    List<UserDto> getPendingReceivedFriendshipRequests(@Param("userId") Long userId);

}
