package com.depart.depart6.Dto;

import lombok.Data;

@Data
public class ChatSummaryDto {
    private String name;
    private String last_message;
    private String image;
    private boolean isOnline;
    private String time;
    private boolean isGroup;

}
