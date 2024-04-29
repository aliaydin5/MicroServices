package com.example.activityProject.Service;

import com.example.activityProject.DTO.ActivityDto;
import com.example.activityProject.DTO.UserDto;
import com.example.activityProject.Entity.Activity;

import java.util.List;

public interface ActivityService {
    List<ActivityDto> getAll();
    ActivityDto save(ActivityDto activityDto);


    ActivityDto update(ActivityDto activityDto);

    String delete(ActivityDto activityDto);

    ActivityDto saveActivityUser(long id, ActivityDto activityDto);

     List<ActivityDto> getActivityDetails(Long activityId);

    String getRabbitMQ(String name);
}
