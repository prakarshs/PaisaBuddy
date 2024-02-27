package com.prakarshs.ExpenseTracker.UserAuthService.Repository;


import com.prakarshs.ExpenseTracker.UserAuthService.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends MongoRepository<User,String> {
    boolean existsByUserEmail(String userEmail);
}
