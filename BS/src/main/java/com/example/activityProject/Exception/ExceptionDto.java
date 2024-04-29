package com.example.activityProject.Exception;

public record ExceptionDto(String timestamp,
                           int statusCode,
                           org.springframework.http.HttpStatus error,

                           String message,
                           String path) {


}
