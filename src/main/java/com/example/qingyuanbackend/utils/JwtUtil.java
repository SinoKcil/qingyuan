package com.example.qingyuanbackend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.refreshExpiration}")
    private long refreshExpiration; // 刷新令牌的有效期

//    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    // 生成访问令牌
    public String generateToken(String userId) {
        return generateTokenWithExpiration(userId, expiration);
    }

    // 生成刷新令牌
    public String generateRefreshToken(String userId) {
        return generateTokenWithExpiration(userId, refreshExpiration);
    }

    // 从令牌中获得userId
    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    // 验证令牌
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
//            log.error("Authentication error: {}", e.getMessage());
        }
        return false;
    }

    // 检查令牌是否过期
    public boolean isTokenExpired(String token) {
        final Date expiration = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

    private String generateTokenWithExpiration(String userId, long expirationMillis) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // 获取令牌过期时间
    public Date getExpirationDateFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
    }

    public String getSecret() {
        return secret;
    }

    public long getExpiration() {
        return expiration;
    }

    public long getRefreshExpiration() {
        return refreshExpiration;
    }
}
