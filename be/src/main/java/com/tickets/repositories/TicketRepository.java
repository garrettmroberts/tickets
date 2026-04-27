package com.tickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickets.model.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
}
