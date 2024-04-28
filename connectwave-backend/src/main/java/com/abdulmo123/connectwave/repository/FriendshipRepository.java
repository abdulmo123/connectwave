package com.abdulmo123.connectwave.repository;

import com.abdulmo123.connectwave.dto.FriendshipDto;
import com.abdulmo123.connectwave.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    /*@Query(value = "select f.* from connectwave.friendship f where f.user_id = :userId", nativeQuery = true)
    List<Friendship> getFriendshipList(@Param("userId") Long userId);*/

    @Query("SELECT f.id, f.user, f.friend, f.status FROM Friendship f WHERE f.user.id = :userId")
    List<Object[]> getFriendshipList(@Param("userId") Long userId);


    @Modifying
    @Query(value = "insert into connectwave.friendship (status, user_id, friend_id) " +
            "values ('PENDING', :userId, :friendId) " +
//            "('PENDING', :friendId, :userId) " +
            "on duplicate key update status = values(status)", nativeQuery = true)
    @Transactional
    void sendFriendshipRequest(@Param("userId") Long userId, @Param("friendId") Long friendId);

    @Query(value = "select f.* from connectwave.friendship f " +
            "where (f.user_id = :userId AND f.friend_id = :friendId) " +
            "OR (f.user_id = :friendId AND f.friend_id = :userId)", nativeQuery = true)
    List<Friendship> exisitingFriendshipRequest(Long userId, Long friendId);

    // query for accepting friend request
    @Modifying
    @Query(value = "UPDATE connectwave.friendship " +
            "SET status = 'FRIEND' " +
            "WHERE (user_id = :userId AND friend_id = :friendId) " +
            "OR (user_id = :friendId AND friend_id = :userId)", nativeQuery = true)
    @Transactional
    void acceptFriendshipRequest(@Param("userId") Long userId, @Param("friendId") Long friendId);

    // query for denying friend request
    @Modifying
    @Query(value = "UPDATE connectwave.friendship " +
            "SET status = 'REJECTED' " +
            "WHERE (user_id = :userId AND friend_id = :friendId) " +
            "OR (user_id = :friendId AND friend_id = :userId)", nativeQuery = true)
    @Transactional
    void rejectFriendshipRequest(@Param("userId") Long userId, @Param("friendId") Long friendId);

    @Modifying
    @Query(value = "DELETE from connectwave.friendship " +
            "WHERE (user_id = :userId AND friend_id = :friendId) " +
            "OR (user_id = :friendId AND friend_id = :userId)", nativeQuery = true)
    @Transactional
    void removeExistingFriendship (@Param("userId") Long userId, @Param("friendId") Long friendId);
}
