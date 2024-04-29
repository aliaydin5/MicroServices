package com.example.activityProject.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class DepartNotFoundExcpetionHandler extends RuntimeException{
    private ExceptionDto exceptionDto;

    public DepartNotFoundExcpetionHandler(ExceptionDto exceptionDto) {
        this.exceptionDto = exceptionDto;
    }

    public DepartNotFoundExcpetionHandler(String message, ExceptionDto exceptionDto) {
        super(message);
        this.exceptionDto = exceptionDto;
    }

    public DepartNotFoundExcpetionHandler(String message, Throwable cause, ExceptionDto exceptionDto) {
        super(message, cause);
        this.exceptionDto = exceptionDto;
    }

    public DepartNotFoundExcpetionHandler(Throwable cause, ExceptionDto exceptionDto) {
        super(cause);
        this.exceptionDto = exceptionDto;
    }

    public DepartNotFoundExcpetionHandler(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ExceptionDto exceptionDto) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionDto = exceptionDto;
    }
}
