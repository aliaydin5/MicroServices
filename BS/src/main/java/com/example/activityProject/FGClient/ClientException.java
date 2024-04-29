package com.example.activityProject.FGClient;

import com.example.activityProject.Exception.DepartNotFoundExcpetionHandler;
import com.example.activityProject.Exception.ExceptionDto;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import javax.security.auth.login.AccountException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ClientException implements ErrorDecoder {
    private final ErrorDecoder errorDecoder=new Default();
    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionDto exceptionDto=null;


        try(InputStream ınputStream=response.body().asInputStream()) {
            exceptionDto=new ExceptionDto((String) response.headers().get("date").toArray()[0],response.status(), HttpStatus.resolve(response.status()), IOUtils.toString(ınputStream, StandardCharsets.UTF_8),response.request().url());


        }catch (IOException ioException){
            return new Exception(ioException.getMessage());
        }
        switch (response.status()){
            case 404:
                throw new DepartNotFoundExcpetionHandler(exceptionDto);
            default:
                return errorDecoder.decode(methodKey,response);
        }
    }
}
