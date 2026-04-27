package com.tickets.services;

import org.springframework.stereotype.Service;

import com.tickets.model.Event;
import com.tickets.repositories.EventRepository;

import com.tickets.dto.CreateEventRequest;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(CreateEventRequest request) {
        Event event = new Event();
        event.setTime(request.time());
        event.setCapacity(request.capacity());
        event.setDate(request.date());
        event.setTitle(request.title());

        return eventRepository.save(event);
    }
}
