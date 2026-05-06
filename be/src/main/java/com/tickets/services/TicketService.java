package com.tickets.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tickets.dto.CreateTicketRequest;
import com.tickets.model.Event;
import com.tickets.model.Ticket;
import com.tickets.repositories.EventRepository;
import com.tickets.repositories.TicketRepository;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    public TicketService(TicketRepository ticketRepository, EventRepository eventRepository) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
    }

    public Ticket createTicket(CreateTicketRequest request) {
        if (request.eventId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "eventId is required");
        }

        Event event = eventRepository.findById(request.eventId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        Ticket ticket = new Ticket();
        ticket.setCost(request.cost());
        ticket.setEvent(event);
        return ticketRepository.save(ticket);
    }
}
