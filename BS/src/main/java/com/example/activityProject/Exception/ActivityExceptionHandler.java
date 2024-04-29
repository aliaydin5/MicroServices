package com.example.activityProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ActivityExceptionHandler {

    @ExceptionHandler(DepartNotFoundExcpetionHandler.class)
    public ResponseEntity<?> handleNotFound(DepartNotFoundExcpetionHandler departNotFoundExcpetionHandler){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(departNotFoundExcpetionHandler.getMessage());
    }
}
