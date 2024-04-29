package com.example.activityProject.Service;

import com.example.activityProject.DTO.ActivityDto;
import com.example.activityProject.DTO.ActivityDtoMessage;
import com.example.activityProject.DTO.RabbitMessage;
import com.example.activityProject.DTO.UserDto;
import com.example.activityProject.Entity.Activity;
import com.example.activityProject.Entity.User;
import com.example.activityProject.Repository.ActivityRepository;
import com.example.activityProject.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class ActivityServiceImp implements ActivityService {
    ModelMapper modelMapper;
    ActivityRepository activityRepository;
    UserRepository userRepository;
    RabbitTemplate rabbitTemplate;

    public ActivityServiceImp(ModelMapper modelMapper, ActivityRepository activityRepository,UserRepository userRepository,RabbitTemplate rabbitTemplate) {
        this.modelMapper = modelMapper;
        this.activityRepository = activityRepository;
        this.userRepository=userRepository;
        this.rabbitTemplate=rabbitTemplate;
    }

    @Override
    public List<ActivityDto> getAll() {
        List<Activity> activity1=activityRepository.findAll();
        List<ActivityDto> activityDto=activity1.stream().map(activity -> modelMapper.map(activity,ActivityDto.class)).collect(Collectors.toList());

        return activityDto;
    }

    @Override
    public ActivityDto save(ActivityDto activityDto) {
        Activity activity1=activityRepository.save(modelMapper.map(activityDto,Activity.class));
        ActivityDto activityDto1=modelMapper.map(activity1,ActivityDto.class);
        return activityDto1;
    }

    @Override
    public ActivityDto update(ActivityDto activityDto) {
        Activity activity=activityRepository.save(modelMapper.map(activityDto,Activity.class));
        ActivityDto activityDto1=modelMapper.map(activity,ActivityDto.class);
        return activityDto1;
    }

    @Override
    public String delete(ActivityDto activityDto) {
        activityRepository.delete(modelMapper.map(activityDto,Activity.class));
        return new String("Silme işlemi başarılı");
    }

    @Override
    public ActivityDto saveActivityUser(long id, ActivityDto activityDto) {
        Activity activity1=modelMapper.map(activityDto,Activity.class);

        Activity activity=activityRepository.findById(activity1.getId());

        User user=userRepository.findById(id);
        if (user!=null){
            Set<User> users=new HashSet<>();
            users.add(user);
            activity.setUsers(users);
            Activity activity2=activityRepository.save(activity);
            ActivityDto activityDto1=modelMapper.map(activity2,ActivityDto.class);
            return activityDto1;
        }else {
            throw new RuntimeException("Kullanıcı bulunamdı!");
        }
    }

    @Override
    public List<ActivityDto> getActivityDetails(Long activityId) {
        List<Activity> activityList=new ArrayList<>();
        activityList=activityRepository.findAllById(activityId);
        if(activityList!=null){
            List<ActivityDto> activitiesList=activityList.stream().map(activity -> modelMapper.map(activity,ActivityDto.class)).collect(Collectors.toList());
            return activitiesList;

        }else {
            ActivityDtoMessage activityDtoMessage=new ActivityDtoMessage("Activite bulunamadı!");
            List<ActivityDto> activityDtoList1= new ArrayList<>();
            activityDtoList1.add(activityDtoMessage);
            return activityDtoList1;
        }

    }

    @Override
    public String getRabbitMQ(String name) {
        RabbitMessage rabbitMessage= new RabbitMessage(1L,name);
        rabbitTemplate.convertAndSend("Direct-Exchange","boun",rabbitMessage);
        return "Success";
    }


}
