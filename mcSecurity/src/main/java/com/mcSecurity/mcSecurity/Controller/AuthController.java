package com.mcSecurity.mcSecurity.Controller;

import com.mcSecurity.mcSecurity.Dto.JWTAuthResponse;
import com.mcSecurity.mcSecurity.Dto.LoginDto;
import com.mcSecurity.mcSecurity.Dto.RegisterDto;
import com.mcSecurity.mcSecurity.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    AuthService authService;
    private Logger logger;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> getToken(@RequestBody LoginDto loginDto){
        String token=authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse=new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String res=authService.register(registerDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);


    }
}
