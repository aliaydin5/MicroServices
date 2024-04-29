package com.example.activityProject.FGClient;

import com.example.activityProject.DTO.Depart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "depart-service",path = "/v1")
public interface ManagementClient {
    @GetMapping("/getAll")
    @CircuitBreaker(label = "defaultCircuitBreaker")
    ResponseEntity<?> getAllDepart();

    default ResponseEntity<?> defaultCircuitBreaker(){
        return ResponseEntity.ok(new Depart(0,"default"));
    }


}
