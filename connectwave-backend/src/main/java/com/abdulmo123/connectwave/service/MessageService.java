package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.dto.MessageDto;
import com.abdulmo123.connectwave.entity.Message;

import java.util.List;

public interface MessageService {


//    Message checkConversationExists(Long conversationId);

    List<MessageDto> getConversationMessages(Long senderId, Long receiverId);

    Message sendMessage(Message message, Long senderId, Long receiverId);
}
