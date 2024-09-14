package com.abdulmo123.connectwave.service;

import com.abdulmo123.connectwave.dto.MessageDto;
import com.abdulmo123.connectwave.dto.UserDto;
import com.abdulmo123.connectwave.entity.Message;
import com.abdulmo123.connectwave.entity.User;
import com.abdulmo123.connectwave.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    /*@Override
    public List<Message> getConversationMessages(Long conversationId) {
        return messageRepository.getConversationMessages(conversationId);
    }*/

    @Override
    public List<MessageDto> getConversationMessages(Long senderId, Long receiverId) {
        List<Object[]> results = messageRepository.getConversationMessages(senderId, receiverId);
        List<MessageDto> allConvoMessagesInfo = new ArrayList<>();

        for (Object [] obj : results) {
            Long id = (Long) obj[0];
            Long conversationId = (Long) obj[1];
            User user = (User) obj[2];
            User friend = (User) obj[3];
            String message = (String) obj[4];
            Date createdDate = (Date) obj[5];


            UserDto userDto = new UserDto(
                    user.getId(), user.getEmail(), user.getFirstName(),
                    user.getLastName(), user.getGender(), user.getBio()
            );

            UserDto friendDto = new UserDto(
                    friend.getId(), friend.getEmail(), friend.getFirstName(),
                    friend.getLastName(), friend.getGender(), friend.getBio()
            );

            MessageDto messageDto = new MessageDto(id, conversationId, userDto, friendDto, message, createdDate);
            allConvoMessagesInfo.add(messageDto);
        }
        return allConvoMessagesInfo;
    }

    @Override
    @Transactional
    public Message sendMessage(Message message, Long senderId, Long receiverId) {
        Message existingConvo = messageRepository.checkConversationExists(senderId, receiverId);
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
