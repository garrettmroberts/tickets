package com.tickets.dto;

import java.sql.Date;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String emailAddr,
        Date birthdate,
        String stripeId,
        String role) {
}
