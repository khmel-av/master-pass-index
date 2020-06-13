package ru.khmel.mpi.configuration.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Khmel Anton
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Value(value = "${Security.TokenPrefix}")
    String tokenPrefix;
    @Value(value = "${Security.HeaderAttr}")
    String headerAttr;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
        throws IOException, ServletException {
        if (!req.getRequestURI().contains("/public/")) {
            String header = req.getHeader(headerAttr);
            String authToken = null;
            if (header != null && header.startsWith(tokenPrefix)) {
                authToken = header.replace(tokenPrefix, "");
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    if (jwtTokenUtil.validateToken(res, authToken)) {
                        UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(null, authToken,
                                Arrays.asList(new SimpleGrantedAuthority("USER")));
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                } else {
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } else {
                log.warn("couldn't find bearer string, will ignore the header");
//                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        chain.doFilter(req, res);
    }
}