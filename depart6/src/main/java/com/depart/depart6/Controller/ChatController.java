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
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/chat.sendMessage")
    public void sendMessage(@RequestBody MessageDto chatMessage) {
        chatMessage.setTime(LocalDateTime.now());// Mesaj zaman damgasını ayarla
        Message message=new Message();
        message.setMessage(chatMessage.getMessage());
        message.setTime(chatMessage.getTime());
        message.set_sender(true);
        messageRepository.save(message);  // Mesajı veritabanına kaydet

        String destination = "/user/" + chatMessage.getReceiverId() + "/queue/messages";
        messagingTemplate.convertAndSendToUser(chatMessage.getReceiverId(), "/queue/messages", chatMessage);
    }

    // Kullanıcılar arasındaki geçmiş mesajları getir
    @GetMapping("/chat/history/{userId}/{receiverId}")
    public List<Message> getChatHistory(@PathVariable String userId, @PathVariable String receiverId) {
        List<Message> messageList = messageRepository.findAll().stream()
                .filter(message -> (message.getSenderId().equals(userId) && message.getReceiverId().equals(receiverId))
                        || (message.getSenderId().equals(receiverId) && message.getReceiverId().equals(userId)))
                .collect(Collectors.toList());

        for (Message message : messageList) {
            if (message.getSenderId().equals(userId)) {
                message.set_sender(true);
                message.set_text(true);
            } else {
                message.set_sender(false);
                message.set_text(true);
            }
        }

        return messageList;
    }

    @GetMapping("/chat/last-messages/{userId}")
    public List<ChatSummaryDto> getLastMessagesForUser(@PathVariable Long userId) {
        List<Message> lastMessages = userRepo.findAll().stream()
                .filter(user -> userId.equals(user.getId())) // Filter users by userId
                .flatMap(user -> user.getMessageSet().stream()) // Flatten the list of messages
                .collect(Collectors.toList()); // Collect to a single list



        //Burada alttaki fordan dolayı brden çok aynı kullanıcı oluşturuyor
        List<ChatSummaryDto> chatSummaries = new ArrayList<>();

        for (Message message : lastMessages) {
            ChatSummaryDto chatSummary = new ChatSummaryDto();
            User user=userRepo.findById(Long.parseLong(message.getReceiverId()));

            // Gönderen ve alıcı bilgilerini belirle
           // String otherUserId = String.valueOf(message.getSenderId().equals(userId) ? message.getReceiverId() : message.getSenderId());
           // User otherUser = userRepo.findById(Long.valueOf(otherUserId)).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

            chatSummary.setName(user.getName());
            chatSummary.setLast_message(message.getMessage()); // Son mesaj içeriği
            chatSummary.setImage(user.getImage()); // Kullanıcı resmi
            chatSummary.setOnline(true); // Kullanıcı online mı?
            chatSummary.setTime(message.getTime().toString()); // Zaman damgası
          //  chatSummary.setGroup(message.isGroupMessage()); // Grup mesajı mı?

            chatSummaries.add(chatSummary);
        }

        return chatSummaries;
    }
}