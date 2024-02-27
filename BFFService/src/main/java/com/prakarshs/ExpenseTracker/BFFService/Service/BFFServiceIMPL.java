package com.prakarshs.ExpenseTracker.BFFService.Service;

import com.prakarshs.ExpenseTracker.BFFService.Constants.FlowLoggers;
import com.prakarshs.ExpenseTracker.BFFService.External.UserAuthService;
import com.prakarshs.ExpenseTracker.BFFService.Model.AuthRequest;
import com.prakarshs.ExpenseTracker.BFFService.Model.AuthResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class BFFServiceIMPL implements BFFService{
    @Autowired
    private UserAuthService userAuthService;
    @Override
    public AuthResponse signup(AuthRequest authRequest) {
        log.info(FlowLoggers.BFF_SIGN_UP_INITIATED);
        AuthResponse authResponse = userAuthService.signup(authRequest).getBody();
        log.info(FlowLoggers.BFF_SIGN_UP_COMPLETED);
        return authResponse;
    }
}
