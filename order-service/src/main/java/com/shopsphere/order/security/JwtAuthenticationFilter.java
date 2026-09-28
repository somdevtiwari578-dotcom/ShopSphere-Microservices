package com.shopsphere.order.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String secret;

    public JwtAuthenticationFilter(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    private SecretKey key() {
        // Creates the same secret key that User Service uses
        // to sign the JWT.
        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Check whether Authorization header exists.
        if (header == null || !header.startsWith("Bearer ")) {

            System.out.println("JWT ERROR: Authorization header missing.");

            filterChain.doFilter(request, response);
            return;
        }

        // Remove "Bearer " from the beginning.
        String token = header.substring(7);

        try {

            // Verify JWT signature and read its claims.
            Claims claims = Jwts.parser()
                    .verifyWith(key())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String email = claims.getSubject();
            String role = claims.get("role", String.class);

            System.out.println("JWT SUCCESS");
            System.out.println("Email: " + email);
            System.out.println("Role: " + role);

            // Create Spring Security authentication object.
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            List.of(
                                    new SimpleGrantedAuthority(
                                            "ROLE_" + role
                                    )
                            )
                    );

            // Store authenticated user in Spring Security context.
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

        } catch (Exception e) {

            // IMPORTANT:
            // Previously the exception was silently ignored.
            // Now we can see the real JWT problem in IntelliJ console.

            System.out.println("JWT ERROR: " + e.getClass().getSimpleName());
            System.out.println("JWT MESSAGE: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
