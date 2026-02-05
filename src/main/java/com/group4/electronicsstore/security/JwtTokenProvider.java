package com.group4.electronicsstore.security;

import com.group4.electronicsstore.entity.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String accessSecret;

    @Value("${jwt.refresh-secret}")
    private String refreshSecret;

    @Value("${jwt.expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    /* ================= KEY ================= */

    private Key accessKey() {
        return Keys.hmacShaKeyFor(
                accessSecret.getBytes(StandardCharsets.UTF_8)
        );
    }

    private Key refreshKey() {
        return Keys.hmacShaKeyFor(
                refreshSecret.getBytes(StandardCharsets.UTF_8)
        );
    }

    /* ================= GENERATE ================= */

    // Access Token
    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + accessExpiration)
                )
                .signWith(accessKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Refresh Token
    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + refreshExpiration)
                )
                .signWith(refreshKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /* ================= PARSE ================= */

    public String getUsernameFromAccessToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(accessKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getUsernameFromRefreshToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(refreshKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /* ================= VALIDATE ================= */

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(accessKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean validateRefreshToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(refreshKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
