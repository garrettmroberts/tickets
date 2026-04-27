package com.tickets.dto;

import java.sql.Date;

public record CreateUserRequest(
    String firstName,
    String lastName,
    String emailAddr,
    Date birthdate,
    String stripeId,
    String password
) {
        
}
