package com.prakarshs.ExpenseTracker.BFFService.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthLoginRequest {
    private String userEmail;
    private String password;
}
