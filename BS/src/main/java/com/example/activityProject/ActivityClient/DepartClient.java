package com.example.activityProject.ActivityClient;

import com.example.activityProject.DTO.Depart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@FeignClient(name = "department-service",path = "/depart/v1")
public interface DepartClient {
     @GetMapping("/getAll")
     ResponseEntity<List<Depart>> getAllDepart();

}
