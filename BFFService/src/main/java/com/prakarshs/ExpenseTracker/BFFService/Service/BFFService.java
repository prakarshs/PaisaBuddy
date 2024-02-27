package com.prakarshs.ExpenseTracker.BFFService.Service;

import com.prakarshs.ExpenseTracker.BFFService.Model.AuthRequest;
import com.prakarshs.ExpenseTracker.BFFService.Model.AuthResponse;

public interface BFFService {
    AuthResponse signup(AuthRequest authRequest);
}
