package com.example.activityProject.DTO;

public class UserDtoMessage extends UserDto{
    private  String message;

    public UserDtoMessage(String message) {
        this.message = message;
    }

    public UserDtoMessage(long id, String username, String message) {
        super(id, username);
        this.message = message;
    }

}
