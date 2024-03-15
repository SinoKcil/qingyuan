package com.example.qingyuanbackend.utils;

import com.example.qingyuanbackend.mapper.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.example.qingyuanbackend.model.User;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.refreshExpiration}")
    private long refreshExpiration; // 刷新令牌的有效期

    @Autowired
    private UserMapper userMapper;

    private SecretKey getSecretKey() {
        // 将密钥字符串转换为SecretKey对象，以用于签名或验证JWT
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

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
        Claims claims = Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    // 验证令牌
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // log.error("Authentication error: {}", e.getMessage());
        }
        return false;
    }

    // 检查令牌是否过期
    public boolean isTokenExpired(String token) {
        final Date expiration = Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

    private String generateTokenWithExpiration(String userId, long expirationMillis) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(getSecretKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // 获取令牌过期时间
    public Date getExpirationDateFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody().getExpiration();
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

    public boolean isAdmin(String token){
        String userId = getUserIdFromToken(token);
        User user = userMapper.findByUsername(userId);
        return user != null && "admin".equals(user.getRole());
    }

}
