package com.depart.depart6.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderId;
    private String receiverId;
    private String message;

    private LocalDateTime time;

    private boolean is_sender;

    private boolean is_text;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id",referencedColumnName = "id")
    @JsonIgnore
    private User userMessage;




}
