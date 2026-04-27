package com.tickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickets.model.Event;


public interface EventRepository extends JpaRepository<Event, Long> {
    
}
