package com.mcSecurity.mcSecurity.Service;

import com.mcSecurity.mcSecurity.Dto.LoginDto;
import com.mcSecurity.mcSecurity.Dto.RegisterDto;
import com.mcSecurity.mcSecurity.Entity.Role;
import com.mcSecurity.mcSecurity.Entity.User;
import com.mcSecurity.mcSecurity.Repository.RoleRepository;
import com.mcSecurity.mcSecurity.Repository.UserRepository;
import com.mcSecurity.mcSecurity.Security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class AuthServiceImpl implements AuthService{
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private RoleRepository roleRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider,RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.roleRepository=roleRepository;

    }

    @Override
    public String login(LoginDto loginDto) {
            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token=jwtTokenProvider.generateToken(authentication);
            return token;










    }

    @Override
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getName())){
            return "eşleşme bulunamadı!";

        }
        if(userRepository.existsByEmail(registerDto.getEmail())){
            return "eşleşme bulunamadı";

        }
        User user=new User();
        user.setUsername(registerDto.getName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());


        Set<Role> roles=new HashSet<>();
        Role role=roleRepository.findByName("ROLE_USER").get();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return "kayıt başarılı";


    }
    public boolean isValidUser(LoginDto loginDto) {
        String username;
        if(loginDto.getUsernameOrEmail().contains("@boun.edu.tr")){
            username=loginDto.getUsernameOrEmail().replace("@boun.edu.tr","");
        }
        else {
            username=loginDto.getUsernameOrEmail();
        }

        User user=userRepository.findByUsername(username);
        boolean response=false;
        if (user!=null&&user.isStatus()==true){
            response=loginWithLDAP(loginDto.getUsernameOrEmail(),loginDto.getPassword());
        }
        return response;
    }

    @Override
    public boolean loginWithLDAP(String user, String password) {
        return false;
    }
}
