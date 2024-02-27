package com.prakarshs.ExpenseTracker.UserAuthService.Exception;

import lombok.Data;

@Data
public class CustomError extends RuntimeException{
    private String resolution;
    public CustomError(String message, String resolution){
        super(message);
        this.resolution = resolution;
    }
}
