package com.prakarshs.ExpenseTracker.UserAuthService.Repository;

import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends MongoRepository<User,String> {
}
