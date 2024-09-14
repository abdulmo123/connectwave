package com.abdulmo123.connectwave.controller;

import com.abdulmo123.connectwave.dto.MessageDto;
import com.abdulmo123.connectwave.entity.Message;
import com.abdulmo123.connectwave.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{senderId}/{receiverId}/getConversationMessages")
    private ResponseEntity<List<MessageDto>> getConversationMessages(@PathVariable("senderId") Long senderId,
                                                                  @PathVariable("receiverId") Long receiverId) {
        List<MessageDto> conversationMessages = messageService.getConversationMessages(senderId, receiverId);
        return new ResponseEntity<>(conversationMessages, HttpStatus.OK);
    }


    @PostMapping("/{senderId}/{receiverId}/sendMessage")
    private ResponseEntity<Message> sendMessage(@RequestBody Message message,
                                                @PathVariable("senderId") Long senderId,
                                                @PathVariable("receiverId") Long receiverId) {

        Message newMessage = messageService.sendMessage(message, senderId, receiverId);
        return new ResponseEntity<>(newMessage, HttpStatus.CREATED);
    }
}
