package ru.khmel.mpi.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

/**
 * @author Khmel Anton
 */
@Component
public class JwtTokenUtil implements Serializable {
    @Value(value = "${Security.SigningKey}")
    String signingKey;

    public Date getExpirationDateFromToken(HttpServletResponse res, String token) {
        try {
            return getClaimFromToken(token, Claims::getExpiration);
        } catch (Exception ex) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            return null;
        }
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
        } catch(Exception ex) {
            throw ex;
        }
    }

    private Boolean isTokenExpired(HttpServletResponse res, String token) {
        final Date expiration = getExpirationDateFromToken(res, token);
        if (expiration != null) {
            return expiration.before(new Date());
        } else {
            return true;
        }
    }

    public Boolean validateToken(HttpServletResponse res, String token) {
        return (!isTokenExpired(res, token));
    }
}
