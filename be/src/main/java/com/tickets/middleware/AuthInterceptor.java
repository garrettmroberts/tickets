package com.tickets.middleware;

import java.util.Set;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tickets.model.User;
import com.tickets.services.AuthTokenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private static final String BEARER_PREFIX = "Bearer ";
    private static final Set<String> ALLOWED_ROLES = Set.of("user", "organizer", "admin");

    private final AuthTokenService authTokenService;

    public AuthInterceptor(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, Object handler) {
        if (isPublicEndpoint(request)) {
            return true;
        }

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header");
        }

        String token = authorizationHeader.substring(BEARER_PREFIX.length()).trim();
        User user = authTokenService.getUserFromToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid auth token"));

        String role = user.getRole();
        if (role == null || !ALLOWED_ROLES.contains(role)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User role is invalid");
        }

        // Attach user context for future authorization checks.
        request.setAttribute("authenticatedUserId", user.getId());
        request.setAttribute("authenticatedUserRole", role);
        return true;
    }

    private boolean isPublicEndpoint(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        return HttpMethod.POST.matches(method) && "/users".equals(uri)
                || HttpMethod.POST.matches(method) && "/users/sign-in".equals(uri);
    }
}
