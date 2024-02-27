package com.prakarshs.ExpenseTracker.UserAuthService.Utils;

import com.prakarshs.ExpenseTracker.UserAuthService.Constants.FlowLoggers;
import com.prakarshs.ExpenseTracker.UserAuthService.Entity.User;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import lombok.extern.log4j.Log4j2;


import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
@Log4j2
public class JwtUtils {
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PB_AUTH_SERVICE = "PB-AUTH-SERVICE";
    public static String generateAccessToken(User user){
        log.info(FlowLoggers.GENERATE_TOKEN_INITIATED);
        String accessToken = Jwts.builder()
                .id(UUID.randomUUID().toString())
                .issuer(PB_AUTH_SERVICE)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(300)))
                .subject(user.getUserEmail())
                .claim("userName",user.getUserName())
                .signWith(SECRET_KEY)
                .compact();
        log.info(FlowLoggers.GENERATE_TOKEN_COMPLETED);

        return accessToken;
    }
}
