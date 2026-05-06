package com.tickets.dto;

public record SignInResponse(
        String token,
        UserResponse user) {
}
