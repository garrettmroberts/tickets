package com.tickets.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tickets.dto.CreateUserRequest;
import com.tickets.model.User;
import com.tickets.repositories.UserRepository;

@Service
public class UserService {
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
        user.setPasswordHash(passwordEncoder.encode(request.password()));

        return userRepository.save(user);
    }
}
