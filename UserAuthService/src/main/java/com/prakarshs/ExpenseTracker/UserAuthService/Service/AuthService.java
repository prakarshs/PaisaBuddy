package com.prakarshs.ExpenseTracker.UserAuthService.Service;

import com.prakarshs.ExpenseTracker.UserAuthService.Model.AuthLoginRequest;
import com.prakarshs.ExpenseTracker.UserAuthService.Model.AuthRequest;
import com.prakarshs.ExpenseTracker.UserAuthService.Model.AuthResponse;

public interface AuthService {
    AuthResponse signup(AuthRequest authRequest);

    AuthResponse login(AuthLoginRequest authLoginRequest);
}
