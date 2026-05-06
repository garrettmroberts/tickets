package com.tickets.dto;

public record SignInRequest(
        String emailAddr,
        String password) {
}
