package com.prakarshs.ExpenseTracker.UserAuthService.Service;

import com.prakarshs.ExpenseTracker.UserAuthService.Constants.CheckLoggers;
import com.prakarshs.ExpenseTracker.UserAuthService.Constants.ExceptionLoggers;
import com.prakarshs.ExpenseTracker.UserAuthService.Constants.FlowLoggers;
import com.prakarshs.ExpenseTracker.UserAuthService.Entity.User;
import com.prakarshs.ExpenseTracker.UserAuthService.Exception.CustomError;
import com.prakarshs.ExpenseTracker.UserAuthService.Model.AuthLoginRequest;
import com.prakarshs.ExpenseTracker.UserAuthService.Model.AuthRequest;
import com.prakarshs.ExpenseTracker.UserAuthService.Model.AuthResponse;
import com.prakarshs.ExpenseTracker.UserAuthService.Repository.AuthRepository;
import com.prakarshs.ExpenseTracker.UserAuthService.Utils.JwtUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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

        log.info(CheckLoggers.USER_EMAIL_EXISTS_CHECK,authRequest.getUserEmail());
        if (authRepository.existsByUserEmail(authRequest.getUserEmail())){
            log.info(CheckLoggers.USER_EMAIL_EXISTS,authRequest.getUserEmail());
            throw new CustomError("The User Email Already Exists.","Try With A Different User Email.");
        }
        log.info(CheckLoggers.USER_EMAIL_NOT_EXISTS,authRequest.getUserEmail());

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

    @Override
    public AuthResponse login(AuthLoginRequest authLoginRequest) {

        User user = authRepository.findByUserEmail(authLoginRequest.getUserEmail()).orElseThrow(()-> new CustomError(ExceptionLoggers.EMAIL_DOESNT_EXIST_IN_DB, ExceptionLoggers.TRY_WITH_A_DIFFERENT_USER_EMAIL));
        AuthResponse authResponse = null;
        if(passwordEncoder.matches(authLoginRequest.getPassword(), user.getPassword())){
            authResponse = AuthResponse.builder()
                    .accessToken(JwtUtils.generateAccessToken(user))
                    .build();
        }
        else throw new CustomError(ExceptionLoggers.WRONG_PASSWORD, ExceptionLoggers.TRY_WITH_A_DIFFERENT_PASSWORD);

        return authResponse;
    }
}
