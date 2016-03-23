package com.pinkpony.model;

import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event implements Serializable {

    public final static String FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssZ";
    private final static DateFormat dateFormat = new SimpleDateFormat(FORMAT_STRING);

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Rsvp> rsvps = new ArrayList<Rsvp>();

    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private String name;
    private String description;

    @JsonIgnore
    private Date eventDateTime;

    private String eventDateTimeString;
    private String organizer;
    private String venue;

    public Event(){}
    public Event(String name){
        this.name = name;
    }

    public String getEventDateTimeString() {
        return eventDateTimeString;
    }

    public void setEventDateTimeString(String eventDateTimeString) {
        this.eventDateTimeString = eventDateTimeString;
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

    public Date getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(Date eventDateTime) {
        this.eventDateTime = eventDateTime;
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
