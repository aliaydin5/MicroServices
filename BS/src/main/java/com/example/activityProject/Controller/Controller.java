package com.example.activityProject.Controller;

import com.example.activityProject.DTO.Depart;
import com.example.activityProject.DTO.LdapDto;
import com.example.activityProject.DTO.UserDto;
import com.example.activityProject.Service.UserService;
import com.google.zxing.WriterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class Controller {

    UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Depart> >getAll() throws IOException, WriterException {



        return ResponseEntity.ok().body(userService.getAll());
    }
    @GetMapping("/user/{user1Id}")
    public ResponseEntity<List<UserDto>> getUserActivities(@PathVariable Long user1Id){

        return ResponseEntity.ok().body(userService.getUserDetails(user1Id));
    }
    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUserId(@PathVariable long userId){
        return new ResponseEntity<>(userService.getId(userId),HttpStatus.OK);
    }
    @GetMapping("/getUserName")
    public ResponseEntity<?> getUserName(@RequestBody UserDto userName){
        return new ResponseEntity<UserDto>(userService.getUserName(userName),HttpStatus.OK);
    }

    @PostMapping("/user1/{User2Id}/{ActivityId}")
    public ResponseEntity<?> saveUserActivities(@PathVariable Long User2Id,@PathVariable Long ActivityId){
        return  new ResponseEntity<>(userService.saveUserActivity(User2Id,ActivityId),HttpStatus.OK);
    }
    @PostMapping("/user1")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){

        return new ResponseEntity<>(userService.save(userDto),HttpStatus.OK);
    }
    @PutMapping("/update/user1")
    public ResponseEntity<?>updateUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.updateUser(userDto),HttpStatus.OK);

    }
    @PutMapping("/user2/{User3Id}/{Activity1Id}")
    public ResponseEntity<?> updateUserActivities(@PathVariable Long User3Id,@PathVariable Long Activity1Id){
        return  new ResponseEntity<>(userService.saveUserActivity(User3Id,Activity1Id),HttpStatus.OK);
    }


    @DeleteMapping("/user/delete")
    public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto){

        return new ResponseEntity<>(userService.deleteUser(userDto),HttpStatus.OK);
    }

}
