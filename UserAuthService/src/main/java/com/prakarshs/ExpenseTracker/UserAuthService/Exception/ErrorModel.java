package com.prakarshs.ExpenseTracker.UserAuthService.Exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorModel {
    private String text;
    private String errorMessage;
    private String resolution;
}
