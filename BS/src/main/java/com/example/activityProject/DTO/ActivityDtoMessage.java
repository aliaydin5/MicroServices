package com.example.activityProject.DTO;

import java.time.LocalDate;

public class ActivityDtoMessage extends ActivityDto{
    private String message;

    public ActivityDtoMessage(long id ,String name, LocalDate expireDate, LocalDate beginDate, String message) {
        super(id,name, expireDate, beginDate);
        this.message = message;
    }

    public ActivityDtoMessage(String message) {
        this.message = message;
    }
}
