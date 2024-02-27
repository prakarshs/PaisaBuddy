package com.prakarshs.ExpenseTracker.UserAuthService.Controller;

import com.prakarshs.ExpenseTracker.UserAuthService.Model.AuthLoginRequest;
import com.prakarshs.ExpenseTracker.UserAuthService.Model.AuthRequest;
import com.prakarshs.ExpenseTracker.UserAuthService.Model.AuthResponse;
import com.prakarshs.ExpenseTracker.UserAuthService.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    private ResponseEntity<AuthResponse> signup(@RequestBody AuthRequest authRequest){
        return new ResponseEntity<>(authService.signup(authRequest), HttpStatus.OK);
    }
    @PostMapping("/login")
    private ResponseEntity<AuthResponse> login(@RequestBody AuthLoginRequest authLoginRequest){
        return new ResponseEntity<>(authService.login(authLoginRequest), HttpStatus.OK);
    }

}
