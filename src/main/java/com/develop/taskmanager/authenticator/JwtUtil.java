package com.develop.taskmanager.authenticator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    private String secretKey = "";

    public String createToken(String tokenFrom) {
        byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);
        secretKey = Base64.getEncoder().encodeToString(key);

        Claims claims = Jwts.claims().setSubject(tokenFrom);
        Date now = new Date();
        long validityInMilliSeconds = 3600000;
        Date validity = new Date(now.getTime() + validityInMilliSeconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    public boolean isValid(String token){
        return !isExpired(token);
    }

    public boolean isExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    public Claims getClaims(String token){
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
