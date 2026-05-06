package com.tickets.services;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.tickets.model.User;
import com.tickets.repositories.UserRepository;

@Service
public class AuthTokenService {
    private final Map<String, Long> tokenToUserId = new ConcurrentHashMap<>();
    private final UserRepository userRepository;

    public AuthTokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String issueToken(User user) {
        String token = UUID.randomUUID().toString();
        tokenToUserId.put(token, user.getId());
        return token;
    }

    public Optional<User> getUserFromToken(String token) {
        Long userId = tokenToUserId.get(token);
        if (userId == null) {
            return Optional.empty();
        }
        return userRepository.findById(userId);
    }
}
