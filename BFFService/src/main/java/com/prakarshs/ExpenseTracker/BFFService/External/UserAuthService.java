package com.prakarshs.ExpenseTracker.BFFService.External;

import com.prakarshs.ExpenseTracker.BFFService.Model.AuthRequest;
import com.prakarshs.ExpenseTracker.BFFService.Model.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PB-AUTH-SERVICE", url = "http://localhost:8080/auth")
public interface UserAuthService {
    @PostMapping("/signup")
    ResponseEntity<AuthResponse> signup(@RequestBody AuthRequest authRequest);

}
