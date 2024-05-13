package com.abdulmo123.connectwave.repository;

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

    // query to list out all the friendships for a sender
    /*@Query("SELECT f.id, f.sender, f.receiver, f.status, f.createdDate " +
            "FROM Friendship f " +
            "WHERE (f.sender.id = :senderId OR " +
            "f.receiver.id = :senderId) " +
            "AND f.status = 'FRIEND'")
    List<Object[]> getFriendshipList(@Param("senderId") Long senderId);*/


    // query that gets all the existing friendship (status = 'FRIEND')
    @Query(value = "SELECT f.* " +
            "FROM connectwave.friendship f " +
            "WHERE (f.sender_id = :senderId OR f.receiver_id = :receiverId) " +
            "AND f.status = 'FRIEND'", nativeQuery = true)
    List<Friendship> existingFriendship(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);


    // query for sending friend requests
    @Modifying
    @Query(value = "insert into connectwave.friendship (status, sender_id, receiver_id, created_date) " +
            "values ('PENDING', :senderId, :receiverId, current_timestamp) " +
            "on duplicate key update status = values(status)", nativeQuery = true)
    @Transactional
    void sendFriendshipRequest(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);


    // query for getting existing friendship request
    @Query(value = "select f.* from connectwave.friendship f " +
            "where (f.sender_id = :senderId AND f.receiver_id = :receiverId) " +
//            "OR (f.sender_id = :receiverId AND f.receiver_id = :senderId)) " +
            "AND f.status = 'PENDING'", nativeQuery = true)
    Friendship existingFriendshipRequest(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);


    // query to get status of friendship request after response
    @Query(value = "select f.* from connectwave.friendship f " +
            "where (f.sender_id = :senderId AND f.receiver_id = :receiverId) " +
//            "OR (f.sender_id = :receiverId AND f.receiver_id = :senderId) " +
            "AND (f.status = 'REJECTED' OR f.status = 'FRIEND')", nativeQuery = true)
    Friendship friendshipRequestStatusAfterResponse(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);


    // query for accepting friend request TODO: adjust query to allow receiver to ACCEPT
    @Modifying
    @Query(value = "UPDATE connectwave.friendship " +
            "SET status = 'FRIEND' " +
            "WHERE (sender_id = :senderId AND receiver_id = :receiverId) "
//            + "OR (sender_id = :receiverId AND receiver_id = :senderId)"
            , nativeQuery = true)
    @Transactional
    void acceptFriendshipRequest(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);


    // query for denying friend request TODO: adjust query to allow receiver to REJECT
    @Modifying
    @Query(value = "UPDATE connectwave.friendship " +
            "SET status = 'REJECTED' " +
            "WHERE (sender_id = :senderId AND receiver_id = :receiverId) "
//            + "OR (sender_id = :receiverId AND receiver_id = :senderId)"
            , nativeQuery = true)
    @Transactional
    void rejectFriendshipRequest(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);


    // query for removing an existing friend
    @Modifying
    @Query(value = "DELETE from connectwave.friendship " +
            "WHERE ((sender_id = :senderId AND receiver_id = :receiverId) " +
            "OR (sender_id = :receiverId AND receiver_id = :senderId)) " +
            "AND status = 'FRIEND'", nativeQuery = true)
    @Transactional
    void removeExistingFriendship (@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);


    // query for grabbing the latest friendship request after making one
    @Query("SELECT f.id, f.sender, f.receiver, f.status, f.createdDate FROM Friendship f " +
            "WHERE f.sender.id = :senderId AND f.receiver.id = :receiverId " +
            "AND f.status = 'PENDING'" +
            "ORDER BY f.createdDate DESC " +
            "LIMIT 1")
    List<Object[]> getNewFriendshipRequest(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);


    /*// query for getting sent pending (sender) friendship requests -> status PENDING
    @Query("SELECT f.id, f.sender, f.receiver, f.status, f.createdDate " +
            "FROM Friendship f " +
            "WHERE f.sender.id = :senderId " +
            "AND f.status = 'PENDING'")
    List<Object[]> getPendingSentFriendshipRequests(@Param("senderId") Long senderId);*/


    /*// query for getting received (friend) friendship requests -> status PENDING
    @Query("SELECT f.id, f.sender, f.receiver, f.status, f.createdDate " +
            "FROM Friendship f " +
            "WHERE f.receiver.id = :receiverId " +
            "AND f.status = 'PENDING'")
    List<Object[]> getPendingReceivedFriendshipRequests(@Param("receiverId") Long receiverId);*/
}
