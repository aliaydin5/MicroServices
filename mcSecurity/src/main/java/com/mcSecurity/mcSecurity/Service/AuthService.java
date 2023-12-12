package com.mcSecurity.mcSecurity.Service;

import com.mcSecurity.mcSecurity.Dto.LoginDto;
import com.mcSecurity.mcSecurity.Dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
    boolean loginWithLDAP(String user, String password);
}
