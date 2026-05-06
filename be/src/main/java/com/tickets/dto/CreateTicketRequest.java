package com.tickets.dto;

public record CreateTicketRequest(
    Long eventId,
    Long cost
) {
}
