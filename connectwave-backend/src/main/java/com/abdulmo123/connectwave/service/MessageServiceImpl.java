package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.model.entity.Message;
import com.abdulmo123.connectwave.model.entity.User;
import com.abdulmo123.connectwave.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Message> getConversationMessages(Long conversationId) {
        return messageRepository.getConversationMessages(conversationId);
    }

    @Override
    public Message checkConversationExists(Long senderId, Long receiverId) {
        return messageRepository.checkConversationExists(senderId, receiverId);
    }

    @Override
    @Transactional
    public Message sendMessage(Message message, Long senderId, Long receiverId) {
        Message existingConvo = checkConversationExists(senderId, receiverId);
        if (existingConvo != null) {
            messageRepository.sendMessage(existingConvo.getConversationId(), message.getMessage(), senderId, receiverId);
        }
        else {
            Message newConvo = new Message();
            User sender = userService.findUserById(senderId);
            User receiver = userService.findUserById(receiverId);
            newConvo.setConversationId(1000 + new Random().nextLong(9000));
            newConvo.setSender(sender);
            newConvo.setReceiver(receiver);

            messageRepository.sendMessage(newConvo.getConversationId(), message.getMessage(), senderId, receiverId);
        }
        return messageRepository.mostRecentSentMessage(message.getConversationId(), senderId, receiverId);
    }
}
