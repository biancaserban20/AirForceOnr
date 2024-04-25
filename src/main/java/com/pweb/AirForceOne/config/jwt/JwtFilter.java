package com.pweb.AirForceOne.config.jwt;

import com.pweb.AirForceOne.exceptions.ExpiredTokenException;
import com.pweb.AirForceOne.exceptions.InvalidTokenException;
import com.pweb.AirForceOne.repository.AdminRepository;
import com.pweb.AirForceOne.repository.CabinCrewMemberRepository;
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
    private final AdminRepository adminRepository;
    private final CabinCrewMemberRepository cabinCrewMemberRepository;
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
        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
        String email = jwtTokenResolver.getEmailFromToken(jwtToken);

        // check for each role (client, admin, cabinCrewMember) if the email exists in the database

        switch (role) {
            case "client" -> {
                var user = clientRepository.findByEmail(email);
                if (user.isEmpty()) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No client found for bearer token");
                    return;
                }
                try {
                    jwtTokenResolver.validateToken(jwtToken,
                            user.get().getEmail(), role);
                } catch (InvalidTokenException | ExpiredTokenException e) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                    return;
                }
            }
            case "admin" -> {
                var user = adminRepository.findByEmail(email);
                if (user.isEmpty()) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No admin found for bearer token");
                    return;
                }

                try {
                    jwtTokenResolver.validateToken(jwtToken,
                            user.get().getEmail(), role);
                } catch (InvalidTokenException | ExpiredTokenException e) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                    return;
                }
            }
            case "cabinCrewMember" -> {
                var user = cabinCrewMemberRepository.findByEmail(email);
                if (user.isEmpty()) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No cabin crew member found for bearer token");
                    return;
                }
                try {
                    jwtTokenResolver.validateToken(jwtToken,
                            user.get().getEmail(), role);
                } catch (InvalidTokenException | ExpiredTokenException e) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
