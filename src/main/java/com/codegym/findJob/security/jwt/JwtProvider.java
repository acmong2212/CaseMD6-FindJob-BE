package com.codegym.findJob.security.jwt;


import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "secret code";
    private int jwtExpiration = 86400;

    public String createToken(String subject, Map<String, Object> claims) {
        return Jwts.builder().setSubject(subject)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e){
            logger.error("Ivalid JWT sinature ->Message: {}", e);
        } catch (MalformedJwtException e){
            logger.error("The token invalid format ->Message: {}",e);
        } catch (UnsupportedJwtException e){
            logger.error("Unsupported JWT toekn ->Message: {}",e);
        } catch (ExpiredJwtException e){
            logger.error("Expired JWT Token -> Message: {}",e);
        } catch (IllegalArgumentException e){
            logger.error("Jwt claims string is empty -> Message {}",e);
        }
        return false;
    }

    public Map<String, Object> getClaims(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    public String getEmailFromToken(String token){
        String email = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return email;
    }
}
