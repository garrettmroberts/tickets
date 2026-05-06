package com.tickets.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tickets.dto.CreateUserRequest;
import com.tickets.dto.SignInRequest;
import com.tickets.dto.UserResponse;
import com.tickets.model.User;
import com.tickets.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        User createdUser = userService.createUser(request);
        return mapToUserResponse(createdUser);
    }

    @PostMapping("/sign-in")
    public UserResponse signIn(@RequestBody SignInRequest request) {
        User user = userService.signIn(request);
        return mapToUserResponse(user);
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmailAddr(),
                user.getBirthdate(),
                user.getStripeId(),
                user.getRole());
    }
}
