package com.mcSecurity.mcSecurity.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final Logger logger= LoggerFactory.getLogger(JwtTokenProvider.class);
    @Value("${app.jwt-secret}")
    private String jwtSec;
    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;
    public String generateToken(Authentication authentication){
        String username=authentication.getName();
        Date date=new Date();
        Date expirationDate=new Date(date.getTime()+jwtExpirationDate);
        String token= Jwts.builder()
                .setSubject(username)
                .setIssuedAt(date)
                .setExpiration(expirationDate)
                .signWith(key())
                .compact();
        return token;
    }
    public Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSec));
    }
    public String getUsername(String token){
        Claims claims=Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String name= claims.getSubject();
        return name;
    }
    public boolean validIsToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;

        }catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
