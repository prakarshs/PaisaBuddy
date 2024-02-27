package com.prakarshs.ExpenseTracker.BFFService.Controller;

import com.prakarshs.ExpenseTracker.BFFService.Model.AuthRequest;
import com.prakarshs.ExpenseTracker.BFFService.Model.AuthResponse;
import com.prakarshs.ExpenseTracker.BFFService.Service.BFFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class BFFController {

    @Autowired
    private BFFService bffService;

    @PostMapping("/signup")
    private ResponseEntity<AuthResponse> signup(@RequestBody AuthRequest authRequest){
        return new ResponseEntity<>(bffService.signup(authRequest), HttpStatus.OK);
    }
}
