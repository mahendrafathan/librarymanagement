package com.glints.librarymanagement.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String secret = "cukupAkuAjaYangTau";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
//        final Date expiration = this.getExpirationDateFromToken(token);
//        return expiration.before(this.generateCurrentDate());
    }
//    public Date getExpirationDateFromToken(String token) {
//        Date expiration;
//        try {
//          final Claims claims = this.getClaimsFromToken(token);
//          expiration = claims.getExpiration();
//        } catch (Exception e) {
//          expiration = null;
//        }
//        return expiration;
//      }
//    private Date generateCurrentDate() {
//        return new Date(System.currentTimeMillis());
//      }
//    private Claims getClaimsFromToken(String token) {
//        Claims claims;
//        try {
//          claims = Jwts.parser()
//            .setSigningKey(this.secret)
//            .parseClaimsJws(token)
//            .getBody();
//        } catch (Exception e) {
//          claims = null;
//        }
//        return claims;
//      }
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}

