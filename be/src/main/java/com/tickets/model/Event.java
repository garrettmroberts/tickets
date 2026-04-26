package com.tickets.model;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Date date;
    private Time time;
    private Long capacity;
    
    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public Date getDate() {
        return this.date;
    }

    public Time getTime() {
        return this.time;
    }

    public Long getCapacity() {
        return this.capacity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setCapacirty(Long cap) {
        this.capacity = cap;
    }
}