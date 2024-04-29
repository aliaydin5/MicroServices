package com.example.activityProject.ActivityClient;

import com.example.activityProject.DTO.Depart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "department-service",path = "/depart/v1")
public interface DepartClient {
     Logger logger= LoggerFactory.getLogger(DepartClient.class);
     @GetMapping("/getAll")
     @CircuitBreaker(label="getAllDepartFallback")
     ResponseEntity<List<Depart>> getAllDepart();
     default ResponseEntity<?> getAllDepartFallback(Exception exception){
          return ResponseEntity.ok("Add new Depart pls");

     }

     @GetMapping("/getActivity/{departId}")
     @CircuitBreaker(label="getAllDepartFallback1")
     ResponseEntity<Depart> getDepartName(@PathVariable(value = "departId") long departId);
     default ResponseEntity<?> getAllDepartFallback1(Exception exception){
          return ResponseEntity.ok("departId value notfound");

     }



}
