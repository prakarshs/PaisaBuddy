package com.prakarshs.ExpenseTracker.UserAuthService.Service;

import com.prakarshs.ExpenseTracker.UserAuthService.Entity.User;
import com.prakarshs.ExpenseTracker.UserAuthService.Exception.CustomError;
import com.prakarshs.ExpenseTracker.UserAuthService.Model.AuthRequest;
import com.prakarshs.ExpenseTracker.UserAuthService.Model.AuthResponse;
import com.prakarshs.ExpenseTracker.UserAuthService.Repository.AuthRepository;
import com.prakarshs.ExpenseTracker.UserAuthService.Utils.JwtUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;

@Service
@Log4j2
public class AuthServiceIMPL implements AuthService{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthRepository authRepository;
    @Override
    public AuthResponse signup(AuthRequest authRequest) {
        log.info("Email: {}",authRequest.getUserEmail());
        log.info("Pass: {}",authRequest.getPassword());

        if (authRepository.existsByUserEmail(authRequest.getUserEmail()))
            throw new CustomError("The User Email Already Exists.","Try With A Different User Email.");

        User user = User.builder()
                .userName(authRequest.getUserName())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .userEmail(authRequest.getUserEmail())
                .userCreatedAt(LocalDateTime.now())
                .build();

        User savedUser = authRepository.save(user);
        log.info("Time: {}",savedUser.getUserCreatedAt());


        return AuthResponse.builder()
                .accessToken(JwtUtils.generateAccessToken(user))
                .build();
    }
}
