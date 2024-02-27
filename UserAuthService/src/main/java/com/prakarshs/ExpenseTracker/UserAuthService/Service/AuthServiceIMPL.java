package com.prakarshs.ExpenseTracker.UserAuthService.Service;

import com.prakarshs.ExpenseTracker.UserAuthService.Entity.User;
import com.prakarshs.ExpenseTracker.UserAuthService.Exception.CustomError;
import com.prakarshs.ExpenseTracker.UserAuthService.Model.AuthRequest;
import com.prakarshs.ExpenseTracker.UserAuthService.Model.AuthResponse;
import com.prakarshs.ExpenseTracker.UserAuthService.Repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class AuthServiceIMPL implements AuthService{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthRepository authRepository;
    @Override
    public AuthResponse signup(AuthRequest authRequest) {

        if (authRepository.findByUserEmail(authRequest.getUserEmail()))
            throw new CustomError("The User Email Already Exists.","Try With A Different User Email");

        User user = User.builder()
                .userName(authRequest.getUserName())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .userEmail(authRequest.getUserEmail())
                .build();



        return AuthResponse.builder()
                .accessToken()
                .build();
    }
}
