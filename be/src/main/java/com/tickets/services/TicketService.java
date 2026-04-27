package com.tickets.services;

import org.springframework.stereotype.Service;

import com.tickets.dto.CreateTicketRequest;
import com.tickets.model.Ticket;
import com.tickets.repositories.TicketRepository;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(CreateTicketRequest request) {
        Ticket ticket = new Ticket();
        ticket.setCost(request.cost());
        return ticketRepository.save(ticket);
    }
}
