package com.tickets.dto;

import java.sql.Date;
import java.sql.Time;

public record CreateEventRequest(
    String title,
    Date date,
    Time time,
    Long capacity
) {

}
