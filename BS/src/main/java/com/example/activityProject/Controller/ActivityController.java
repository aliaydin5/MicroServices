package com.example.activityProject.Controller;

import com.example.activityProject.DTO.ActivityDto;
import com.example.activityProject.DTO.UserDto;
import com.example.activityProject.Entity.User;
import com.example.activityProject.Service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.LinkOption;
import java.util.List;

@RestController
public class ActivityController {
    ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/Activity/getAll")
    public ResponseEntity<List<ActivityDto>> getActivityAll(){

        return ResponseEntity.ok().body(activityService.getAll());
    }
    @GetMapping("/Activity/name")
    public ResponseEntity<String> getRabbitMQ(@RequestBody ActivityDto activityDto){
        return new ResponseEntity<>(activityService.getRabbitMQ(activityDto.getActivityName()),HttpStatus.OK);
    }
    @GetMapping("/Activity/{activityId}")
    public ResponseEntity<?> getActivityDetails(@PathVariable Long activityId){
        return new ResponseEntity<>(activityService.getActivityDetails(activityId),HttpStatus.OK);
    }

    @PostMapping("/Activity/save")
    public ResponseEntity<?> save(@RequestBody ActivityDto activityDto){
        return new ResponseEntity<>(activityService.save(activityDto), HttpStatus.OK);
    }
    @PostMapping("/Activity/{id}")
    public ResponseEntity<?> saveActivityUsers(@PathVariable long id , @RequestBody ActivityDto activityDto ){
        return new ResponseEntity<>(activityService.saveActivityUser(id,activityDto),HttpStatus.OK);
    }
    @PutMapping("/Activity/update")
    public ResponseEntity<?> updateActivity(@RequestBody ActivityDto activityDto){
        return new ResponseEntity<>(activityService.update(activityDto),HttpStatus.OK);
    }
    @DeleteMapping("/Activity/delete")
    public ResponseEntity<?> deleteActivity(@RequestBody ActivityDto activityDto){

        return new ResponseEntity<>(activityService.delete(activityDto),HttpStatus.OK);
    }
}
