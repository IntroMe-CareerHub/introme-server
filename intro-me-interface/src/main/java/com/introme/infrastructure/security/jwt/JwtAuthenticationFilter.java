package com.introme.infrastructure.security.jwt;

import com.introme.user.entity.User;
import com.introme.user.service.UserQuery;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserQuery userQuery;

    public JwtAuthenticationFilter(JwtService jwtService, UserQuery userQuery) {
        this.jwtService = jwtService;
        this.userQuery = userQuery;
    }

    private static final String[] EXCLUDED_PATHS = {"/login/oauth2/code", "/oauth2/authorization","/login-success"};


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        for (String excludedPath : EXCLUDED_PATHS) {
            if (path.startsWith(excludedPath)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        try {
            String token = jwtService.extractAccessToken(request)
                    .filter(jwtService::isTokenValid)
                    .orElseThrow(IllegalAccessException::new);
            setAuthentication(jwtService.extractUserId(token), jwtService.extractEmail(token));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(Long userId, String email) {
        User user = userQuery.getUser(userId, email);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
