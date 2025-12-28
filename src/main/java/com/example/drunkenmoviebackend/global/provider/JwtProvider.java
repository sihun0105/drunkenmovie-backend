package com.example.drunkenmoviebackend.global.provider;

import com.example.drunkenmoviebackend.domain.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.time.Duration;
import java.util.Date;

public class JwtProvider {

    private final String accessSecret;
    private final Duration accessExpire;
    private final String refreshSecret;
    private final Duration refreshExpire;

    public JwtProvider(
            String accessSecret,
            Duration accessExpire,
            String refreshSecret,
            Duration refreshExpire
    ) {
        this.accessSecret = accessSecret;
        this.accessExpire = accessExpire;
        this.refreshSecret = refreshSecret;
        this.refreshExpire = refreshExpire;
    }

    public String createAccessToken(Member member) {
        return Jwts.builder()
                .setSubject(member.getEmail())
                .claim("userId", member.getId())
                .setExpiration(new Date(
                        System.currentTimeMillis() + accessExpire.toMillis()
                ))
                .signWith(Keys.hmacShaKeyFor(accessSecret.getBytes()))
                .compact();
    }

    public String createRefreshToken(String accessToken) {
        return Jwts.builder()
                .claim("accessToken", accessToken)
                .setExpiration(new Date(
                        System.currentTimeMillis() + refreshExpire.toMillis()
                ))
                .signWith(Keys.hmacShaKeyFor(refreshSecret.getBytes()))
                .compact();
    }
}
