package com.pinkpony.model;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
public class Rsvp {
    @ManyToOne
    @JoinColumn(name = "calendar_event_id")
    public CalendarEvent calendarEvent;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String name;
    String response;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response.toLowerCase();
    }

    public Long getId() {
        return id;
    }
}
