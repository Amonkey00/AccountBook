package com.example.accountbook.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {

    public static final String JWT_ID = UUID.randomUUID().toString();

    public static final String JWT_ISSUER = "ACCOUNTBOOK_JWTPROVIDER";
    public static final String JWT_SUBJECT = "LOGIN_TOKEN";

    // JWT secret
    public static final String JWT_SECRET = "JWT#account@book$secretKey9849898125498";

    // Expire time
    public static final int EXPIRE_TIME = 60 * 60 * 1000;

    public static String buildJwt(Integer userId) {

        // Header information
        Map<String,Object> header = new HashMap<>();
        header.put("typ", "JWT");

        // Payload information
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId", userId);

        // Create jwt create_time
        long nowTime = System.currentTimeMillis();
        Date issuedDate = new Date(nowTime);

        // Choose algorithm
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        JwtBuilder builder = Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(issuedDate)
                .setIssuer(JWT_ISSUER)
                .setSubject(JWT_SUBJECT)
                .signWith(signatureAlgorithm, JWT_SECRET);

        if (EXPIRE_TIME > 0) {
            long exp = nowTime + EXPIRE_TIME;
            builder.setExpiration(new Date(exp));
        }

        return builder.compact();
    }

    public static Claims parseJwt(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
    }


}
