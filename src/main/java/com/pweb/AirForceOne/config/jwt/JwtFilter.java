package com.pweb.AirForceOne.config.jwt;

import com.pweb.AirForceOne.exceptions.ExpiredTokenException;
import com.pweb.AirForceOne.exceptions.InvalidTokenException;
import com.pweb.AirForceOne.repository.ClientRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final ClientRepository clientRepository;
    private final JwtTokenResolver jwtTokenResolver;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bearer token not present");
            return;
        }

        String jwtToken = authHeader.substring(7);
        var user = clientRepository.findByEmail(jwtTokenResolver.getEmailFromToken(jwtToken));

        if (user.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No user found for bearer token");
            return;
        }

        try {
            jwtTokenResolver.validateToken(jwtToken,
                    user.get().getEmail());
        } catch (InvalidTokenException | ExpiredTokenException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);
    }
}
