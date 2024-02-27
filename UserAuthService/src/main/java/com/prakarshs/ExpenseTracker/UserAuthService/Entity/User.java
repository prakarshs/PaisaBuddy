package com.prakarshs.ExpenseTracker.UserAuthService.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "user_details")
public class User {
    @Id
    private String userId;
    private String userName;
    private String userEmail;
    private String password;
    @CreatedDate
    private LocalDateTime userCreatedAt;

}
