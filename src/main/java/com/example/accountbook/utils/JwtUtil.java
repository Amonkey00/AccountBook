package com.example.accountbook.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
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
    public static final long EXPIRE_TIME = 3 * 60 * 60 * 1000;

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
                .setId(String.valueOf(userId))
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

    /**
     * 刷新 token 过期时间
     */
    public static String freshJwt(String token) {
        String result = "";
        Claims claims = parseJwt(token);
        Integer userId = (Integer) claims.get("userId");
        result = buildJwt(userId);
        return result;
    }

    public static boolean checkJwt(String token) {
        if (StringUtils.isBlank(token)) return false;
        Claims claims = null;
        try {
            claims = parseJwt(token);
        }catch (Exception e) {
            return false;
        }
        long issuedAt = claims.getIssuedAt().getTime();
        if (claims.getExpiration() == null) return true;
        long expireTime = claims.getExpiration().getTime();
        long nowTimeStamp = System.currentTimeMillis();
        // Token expired
        if (issuedAt + expireTime < nowTimeStamp) {
            return false;
        }
        return true;
    }

    public static Integer parseToken2Id(String token) {
        Integer userId = -1;
        try {
            Claims claims = JwtUtil.parseJwt(token);
            userId = (Integer) claims.get("userId");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }

    public static void main(String[] args) throws InterruptedException {
        String s = buildJwt(2);
        System.out.println(s);
    }

}
