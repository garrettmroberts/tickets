package com.tickets.services;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.tickets.dto.CreateUserRequest;
import com.tickets.dto.SignInRequest;
import com.tickets.model.User;
import com.tickets.repositories.UserRepository;

@Service
public class UserService {
    private static final Set<String> ALLOWED_ROLES = Set.of("user", "organizer", "admin");

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmailAddr(request.emailAddr());
        user.setBirthdate(request.birthdate());
        user.setStripeId(request.stripeId());
        user.setRole(resolveRole(request.role()));
        user.setPasswordHash(passwordEncoder.encode(request.password()));

        return userRepository.save(user);
    }

    public User signIn(SignInRequest request) {
        User user = userRepository.findByEmailAddr(request.emailAddr())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        return user;
    }

    private String resolveRole(String requestedRole) {
        if (requestedRole == null || requestedRole.isBlank()) {
            return "user";
        }

        String normalizedRole = requestedRole.trim().toLowerCase();
        if (!ALLOWED_ROLES.contains(normalizedRole)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Role must be one of: user, organizer, admin");
        }

        return normalizedRole;
    }
}
