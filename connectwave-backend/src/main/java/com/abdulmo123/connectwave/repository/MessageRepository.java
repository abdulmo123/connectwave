package com.abdulmo123.connectwave.repository;

import com.abdulmo123.connectwave.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // query to get list of messages for a given conversation between 2 users
    @Query(value = "select m.* from connectwave.messages m " +
            "where conversation_id = :conversationId", nativeQuery = true)
    List<Message> getConversationMessages(@Param("conversationId") Long conversationId);

    // query to check if a conversation exists between 2 users
    @Query(value = "select m.* from connectwave.messages m " +
            "where (sender_id = :senderId AND receiver_id = :receiverId " +
            "OR sender_id = :receiverId AND receiver_id = :senderId) " +
            "limit 1", nativeQuery = true)
    Message checkConversationExists(@Param("senderId") Long senderId,
                                @Param("receiverId") Long receiverId);

    // query to add a message to the database based on the sender, receiver, and conversation id's
    @Modifying
    @Query(value = "INSERT INTO connectwave.messages " +
            "(conversation_id, created_date, message, receiver_id, sender_id) " +
            "VALUES(:conversationId, current_timestamp, :message, :receiverId, :senderId)", nativeQuery = true)
    int sendMessage(@Param("conversationId") Long conversationId,
                     @Param("message") String message,
                     @Param("senderId") Long senderId,
                     @Param("receiverId") Long receiverId);

    // query to get back the most recent message sent
    @Query(value = "select m.* from connectwave.messages m " +
            "where (sender_id = :senderId AND receiver_id = :receiverId " +
            "OR sender_id = :receiverId AND receiver_id = :senderId) " +
            "AND conversation_id = :conversationId " +
            "order by created_date desc " +
            "limit 1", nativeQuery = true)
    Message mostRecentSentMessage(@Param("conversationId") Long conversationId,
                                  @Param("senderId") Long senderId,
                                  @Param("receiverId") Long receiverId);

}
