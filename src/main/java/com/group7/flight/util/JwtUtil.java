package com.group7.flight.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

@Slf4j
public class JwtUtil {

    private static int time = 7 * 60 * 60 * 24;
    private static final String signature = "flight";

    public static String createToken(String username) {
        Calendar instance = Calendar.getInstance();

        instance.add(Calendar.SECOND, time);

        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(instance.getTime())
                .signWith(SignatureAlgorithm.HS512, signature)
                .compact();
    }

    /**
     * Verify token validity
     */
    public static Boolean isTokenExpired(String token){
        log.error(token);
        Date expiration = getTokenClaim(token).getExpiration();
        return expiration.before(new Date());
    }


    public static Claims getTokenClaim(String token) {
        log.info("JWTUtil: " + token);
        Claims claims = Jwts.parser()
                .setSigningKey(signature).parseClaimsJws(token).getBody();
        return claims;
    }

    public static String getUsernameFromToken(String token) {
        return getTokenClaim(token).getSubject();
    }
}
