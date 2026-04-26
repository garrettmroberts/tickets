package com.tickets.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    @JsonBackReference
    private Event event;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_ids", nullable = true)
    private Ticket tickets;

    private Long cost;

    public Long getId() {
        return this.id;
    }

    public Long getCost() {
        return this.cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }


}
