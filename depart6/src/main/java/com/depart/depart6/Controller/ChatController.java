package com.depart.depart6.Controller;

import com.depart.depart6.Dto.MessageDto;
import com.depart.depart6.Entity.Message;
import com.depart.depart6.Repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;


    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ModelMapper modelMapper;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload MessageDto chatMessage) {
        chatMessage.setTimestamp(LocalDateTime.now());// Mesaj zaman damgasını ayarla
        messageRepository.save(modelMapper.map(chatMessage,Message.class));  // Mesajı veritabanına kaydet

        String destination = "/user/" + chatMessage.getReceiverId() + "/queue/messages";
        messagingTemplate.convertAndSendToUser(chatMessage.getReceiverId(), "/queue/messages", chatMessage);
    }

    // Kullanıcılar arasındaki geçmiş mesajları getir
    @GetMapping("/chat/history/{senderId}/{receiverId}")
    public List<Message> getChatHistory(@PathVariable String senderId, @PathVariable String receiverId) {
        return messageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByTimestampAsc(
                senderId, receiverId, senderId, receiverId);
    }
}
