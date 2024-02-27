package com.prakarshs.ExpenseTracker.UserAuthService.Service;

import com.prakarshs.ExpenseTracker.UserAuthService.Constants.CheckLoggers;
import com.prakarshs.ExpenseTracker.UserAuthService.Constants.FlowLoggers;
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
        log.info(FlowLoggers.SIGN_UP_INITIATED);

        log.info(CheckLoggers.USER_EMAIL_EXISTS_CHECK);
        if (authRepository.existsByUserEmail(authRequest.getUserEmail())){
            log.info(CheckLoggers.USER_EMAIL_EXISTS);
            throw new CustomError("The User Email Already Exists.","Try With A Different User Email.");
        }
        log.info(CheckLoggers.USER_EMAIL_NOT_EXISTS);

        User user = User.builder()
                .userName(authRequest.getUserName())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .userEmail(authRequest.getUserEmail())
                .userCreatedAt(LocalDateTime.now())
                .build();

        authRepository.save(user);

        AuthResponse authResponse = AuthResponse.builder()
                .accessToken(JwtUtils.generateAccessToken(user))
                .build();

        log.info(FlowLoggers.SIGN_UP_COMPLETED);

        return authResponse;
    }
}
