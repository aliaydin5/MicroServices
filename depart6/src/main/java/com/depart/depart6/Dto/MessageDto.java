package com.depart.depart6.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private String senderId;
    private String receiverId;
    private String message;

    private LocalDateTime time;
}
