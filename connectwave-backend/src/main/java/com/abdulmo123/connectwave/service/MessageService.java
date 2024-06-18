package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.entity.Message;

import java.util.List;

public interface MessageService {

    List<Message> getConversationMessages(Long conversationId);

    Message checkConversationExists(Long senderId, Long receiverId);

    Message sendMessage(Message message, Long senderId, Long receiverId);
}
