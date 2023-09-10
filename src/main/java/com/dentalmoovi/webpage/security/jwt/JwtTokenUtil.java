package com.dentalmoovi.webpage.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.dentalmoovi.webpage.services.CacheSer;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil{

    @Autowired
    private CacheSer cacheSer;

    private static final long JWT_TOKEN_VALIDITY = 5L * 60L * 60L; // 5 hours in seconds

    @Value("${jwt.secret}")
    private String secret;

    // Method to generate a JWT token from UserDetails
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        var roles = userDetails.getAuthorities().stream().collect(Collectors.toList()).get(0);
        claims.put("roles", roles);
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // Method to validate a JWT token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getEmailFromToken(token);

        if ( username.equals(userDetails.getUsername()) && Boolean.FALSE.equals(isTokenExpired(token))) {
            return true; // Token valid and not expired
        }

        // Token not valid or it has been expired, so clear the context
        SecurityContextHolder.clearContext();
        return false; // Token not valid or it has been expired
    }

    // Method to get the username or email from JWT token
    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    // Method to verify if JWT token has expired
    private Boolean isTokenExpired(String token) {
        String blackList = cacheSer.getFromBlackListCache(token);

        if (blackList != null) {
            return true; // Return true if the token is in the cache
        }

        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Method to get the expiration from JWT token
    private Date getExpirationDateFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
    }

    // Method to generate a token with the claims and the username provided
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + JWT_TOKEN_VALIDITY * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // Method to expire a JWT token
    public void expireToken(String token) {

        //Caffeine<Object, Object> blackListConfig = blackListConfig();
        cacheSer.addToOrUpdateRegistrationCache(token, "expired");

    }

}
