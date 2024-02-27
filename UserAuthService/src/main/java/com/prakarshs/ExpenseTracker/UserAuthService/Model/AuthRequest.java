package com.prakarshs.ExpenseTracker.UserAuthService.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequest {
    private String userName;
    private String userEmail;
    private String password;
}
