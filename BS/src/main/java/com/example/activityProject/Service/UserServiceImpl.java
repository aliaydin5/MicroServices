package com.example.activityProject.Service;

import com.example.activityProject.ActivityClient.DepartClient;
import com.example.activityProject.DTO.*;
import com.example.activityProject.Entity.Activity;
import com.example.activityProject.Entity.User;
import com.example.activityProject.GlobalException.StudentAlreadyExistsException;
import com.example.activityProject.QR.QRGenerator;
import com.example.activityProject.Repository.ActivityRepository;
import com.example.activityProject.Repository.UserRepository;
import com.google.zxing.WriterException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    DepartClient departClient;
    UserRepository userRepository;
    ModelMapper modelMapper;
    ActivityRepository activityRepository;

   @Autowired
    public UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper,ActivityRepository activityRepository,DepartClient departClient) {
        this.userRepository = userRepository;
        this.modelMapper=modelMapper;
        this.activityRepository=activityRepository;
        this.departClient=departClient;

    }

    @Override
    public List<Depart> getAll() throws IOException, WriterException {
        /*List<User> users= userRepository.findByStatus(true);
        List<UserDto> userDto= users.stream().map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
        if (userDto!=null){

            for (UserDto userDto1:userDto){
                QRGenerator.generateQR(userDto1);

            }
        }*/
        List<Depart> departs=departClient.getAllDepart().getBody().stream().collect(Collectors.toList());
        return departs;






    }

    @Override
    public UserDto getUserName(UserDto userDto) {
        User user=modelMapper.map(userDto,User.class);
        User user2=userRepository.findByUsername(user.getUsername());
        UserDto userDto1=modelMapper.map(user2,UserDto.class);
        if (user2!=null&&user.isStatus()==true){
            return userDto1;
        }else {
            throw new StudentAlreadyExistsException("Kullanıcı bulunmadı");


        }


    }

    @Override
    public UserDto save(UserDto userDto) {
        User user1=userRepository.save(modelMapper.map(userDto,User.class));
        UserDto userDto1=modelMapper.map(user1,UserDto.class);

        return userDto1;

    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user1=userRepository.save(modelMapper.map(userDto,User.class));
        UserDto userDto1=modelMapper.map(user1,UserDto.class);

        return userDto1;
    }

    @Override
    public String deleteUser(UserDto userDto) {
       User user=modelMapper.map(userDto,User.class);
       User user1=userRepository.findByUsername(user.getUsername());
       if (user1!=null&&user1.isStatus()==true){
           user1.setStatus(false);
           return new String("Silme işlemi başarılı");
       }

        return new String("Kullanıcı zaten db de kayıtlı değil!");
    }

    @Override
    public UserDto getId(long userId) {
        User user=userRepository.findById(userId);
        UserDto userDto=modelMapper.map(user,UserDto.class);
        return userDto;

    }

    @Override
    public List<UserDto> getUserDetails(Long user1Id) {
        List<User> users=new ArrayList<>();
        users=userRepository.findAllById(user1Id);
        if (users!=null){
            List<UserDto> userDtoList=users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
            return userDtoList;

        }else{
            UserDtoMessage userDtoMessage=new UserDtoMessage("Kullanıcı bulunamadı!");
            List<UserDto> userDtoList=new ArrayList<>();
            userDtoList.add(userDtoMessage);
            return userDtoList;
        }
    }

    @Override
    public UserDto saveUserActivity(Long user2Id, Long activityId) {
        Set<Activity> activitySet=new HashSet<>();
        User user=userRepository.findById(user2Id).get();
        Activity activity=activityRepository.findById(activityId).get();
        activitySet=user.getActivity();
        activitySet.add(activity);
        user.setActivity(activitySet);
        userRepository.save(user);
        return modelMapper.map(user,UserDto.class);



    }



}
