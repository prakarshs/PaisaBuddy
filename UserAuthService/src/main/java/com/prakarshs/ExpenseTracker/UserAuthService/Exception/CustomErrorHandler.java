package com.prakarshs.ExpenseTracker.UserAuthService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    private ResponseEntity<ErrorModel> customError(CustomError exception){
        return new ResponseEntity<>(ErrorModel.builder()
                .text("!----- YOU ENCOUNTERED AN ERROR! -----!")
                .errorMessage(exception.getMessage())
                .resolution(exception.getResolution())
                .build(), HttpStatus.NOT_FOUND);
    }
}
