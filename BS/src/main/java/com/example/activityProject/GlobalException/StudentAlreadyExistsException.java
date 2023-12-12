package com.example.activityProject.GlobalException;

public class StudentAlreadyExistsException extends RuntimeException{
    public StudentAlreadyExistsException(String message){
        super(message);
    }
}
