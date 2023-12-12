package com.example.activityProject.Service;

import com.example.activityProject.DTO.Depart;
import com.example.activityProject.DTO.LdapDto;
import com.example.activityProject.DTO.UserDto;
import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<Depart> getAll() throws IOException, WriterException;
    UserDto getUserName(UserDto userDto);
    UserDto save(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    String deleteUser(UserDto userDto);

    UserDto getId(long userId);


    List<UserDto> getUserDetails(Long user1Id);

    UserDto saveUserActivity(Long user2Id, Long activityId);


}
