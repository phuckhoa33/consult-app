package com.consult_app.demo.config;

import com.consult_app.demo.mappers.AdminMapper;
import com.consult_app.demo.models.Admin;
import com.consult_app.demo.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Autowired
    AdminMapper adminMapper;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User userDetails = (User) this.userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        userDetails.getAuthorities()

                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)

                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else if (validateRoleId(userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                response.sendError(HttpStatus.FORBIDDEN.value(), "Access Denied: Insufficient Role");
                return;
            }
        }

        filterChain.doFilter(request, response);

    }

    private boolean validateRoleId(User user) {
        // Implement your logic to check the roleId
        // For example, if roleId is 2, return true; otherwise, return false.
        // Replace this with your actual logic based on your UserDetails
        // implementation.i
        Admin admin = adminMapper.getAdminByEmail(user.getEmail());
        return admin != null;
    }

}