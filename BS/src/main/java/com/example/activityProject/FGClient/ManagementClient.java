package com.example.activityProject.FGClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "depart-service",path = "/v1")
public interface ManagementClient {


}
