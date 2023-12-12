package com.example.activityProject.DTO;

import com.example.activityProject.Entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVm {

    private String name;
    private  String message;

    public UserVm(User user, String message) {
        this.setName(user.getUsername());
        this.message = message;
    }
}
