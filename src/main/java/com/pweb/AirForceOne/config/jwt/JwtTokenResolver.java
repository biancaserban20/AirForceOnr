package com.pweb.AirForceOne.config.jwt;

import com.pweb.AirForceOne.exceptions.ExpiredTokenException;
import com.pweb.AirForceOne.exceptions.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.security.Key;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@NoArgsConstructor
public class JwtTokenResolver implements Serializable {
    @Serial
    private static final long serialVersionUID = -2550185165626007488L;
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000L;

    @Value("${app.secret}")
    private String secret;

    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes()).build()
                .parseClaimsJws(token).getBody();
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private boolean isTokenExpired(String token) {
        return getClaimFromToken(token, Claims::getExpiration)
                .before(new Timestamp(System.currentTimeMillis()));
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        long generationTime = System.currentTimeMillis();
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Timestamp(generationTime))
                .setExpiration(new Timestamp(generationTime + JWT_TOKEN_VALIDITY))
                .signWith(key, SignatureAlgorithm.HS512).compact();
    }

    public String generateJwtToken(final String subject) {
        return doGenerateToken(new HashMap<>(), subject);
    }

    public String getEmailFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public void validateToken(String token, String subject)
            throws InvalidTokenException, ExpiredTokenException {
        if (!subject.equals(getEmailFromToken(token))) {
            throw new InvalidTokenException();
        }
        if (isTokenExpired(token)) {
            throw new ExpiredTokenException();
        }
    }
}
