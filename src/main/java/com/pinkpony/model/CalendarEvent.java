package com.pinkpony.model;

import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class CalendarEvent implements Serializable {

    public final static String FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssZ";
    private final static DateFormat dateFormat = new SimpleDateFormat(FORMAT_STRING);
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    @OneToMany(mappedBy = "calendarEvent", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Rsvp> rsvps = new ArrayList<Rsvp>();
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @JsonIgnore
    private Date calendarEventDateTime;

    @JsonProperty("calendarEventDateTime")
    private String calendarEventDateTimeString;
    private String organizer;
    private String venue;

    private Boolean cancelled = false;

    public CalendarEvent(){}

    public CalendarEvent(String name){
        this.name = name;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getCalendarEventDateTimeString() {
        return calendarEventDateTimeString;
    }

    public void setCalendarEventDateTimeString(String calendarEventDateTimeString) {
        this.calendarEventDateTimeString = calendarEventDateTimeString;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCalendarEventDateTime() {
        return calendarEventDateTime;
    }

    public void setCalendarEventDateTime(Date calendarEventDateTime) {
        this.calendarEventDateTime = calendarEventDateTime;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}