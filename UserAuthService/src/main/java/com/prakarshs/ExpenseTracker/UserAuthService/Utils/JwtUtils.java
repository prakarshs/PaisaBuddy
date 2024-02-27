package com.prakarshs.ExpenseTracker.UserAuthService.Utils;

import com.prakarshs.ExpenseTracker.UserAuthService.Entity.User;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
public class JwtUtils {
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    @Value("${spring.application.name}")
    private static String applicationName;
    public static String generateAccessToken(User user){

       return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .issuer(applicationName)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(300)))
                .subject(user.getUserEmail())
                .claim("username",user.getUserName())
                .signWith(SECRET_KEY)
                .compact();

    }
}
