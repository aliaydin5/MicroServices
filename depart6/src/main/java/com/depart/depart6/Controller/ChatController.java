package com.depart.depart6.Controller;

import com.depart.depart6.Dto.ChatSummaryDto;
import com.depart.depart6.Dto.MessageDto;
import com.depart.depart6.Entity.Message;
import com.depart.depart6.Entity.User;
import com.depart.depart6.Repository.MessageRepository;
import com.depart.depart6.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/message")
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;


    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ModelMapper modelMapper;


    @Autowired
    UserRepo userRepo;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload MessageDto chatMessage) {
        chatMessage.setTime(LocalDateTime.now());// Mesaj zaman damgasını ayarla
        messageRepository.save(modelMapper.map(chatMessage,Message.class));  // Mesajı veritabanına kaydet

        String destination = "/user/" + chatMessage.getReceiverId() + "/queue/messages";
        messagingTemplate.convertAndSendToUser(chatMessage.getReceiverId(), "/queue/messages", chatMessage);
    }

    // Kullanıcılar arasındaki geçmiş mesajları getir
    @GetMapping("/chat/history/{userId}/{receiverId}")
    public List<Message> getChatHistory(@PathVariable String userId, @PathVariable String receiverId) {
        return messageRepository.findAll().stream().filter(message -> message.getSenderId()==userId || message.getReceiverId()==receiverId && message.getSenderId()==receiverId || message.getReceiverId()==userId).collect(Collectors.toList());
    }

    @GetMapping("/chat/last-messages/{userId}")
    public List<ChatSummaryDto> getLastMessagesForUser(@PathVariable Long userId) {
        List<Message> lastMessages = userRepo.findAll().stream()
                .filter(user -> userId.equals(user.getId())) // Filter users by userId
                .flatMap(user -> user.getMessageSet().stream()) // Flatten the list of messages
                .collect(Collectors.toList()); // Collect to a single list




        List<ChatSummaryDto> chatSummaries = new ArrayList<>();

        for (Message message : lastMessages) {
            ChatSummaryDto chatSummary = new ChatSummaryDto();
            User user=userRepo.findById(Long.parseLong(message.getReceiverId()));

            // Gönderen ve alıcı bilgilerini belirle
           // String otherUserId = String.valueOf(message.getSenderId().equals(userId) ? message.getReceiverId() : message.getSenderId());
           // User otherUser = userRepo.findById(Long.valueOf(otherUserId)).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

            chatSummary.setName(user.getName());
            chatSummary.setLast_message(message.getMessage()); // Son mesaj içeriği
           // chatSummary.setImage(otherUser.getImage()); // Kullanıcı resmi
           chatSummary.setOnline(true); // Kullanıcı online mı?
            chatSummary.setTime(message.getTime().toString()); // Zaman damgası
          //  chatSummary.setGroup(message.isGroupMessage()); // Grup mesajı mı?

            chatSummaries.add(chatSummary);
        }

        return chatSummaries;
    }
}
