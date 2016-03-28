package com.pinkpony.repository;

import com.pinkpony.model.CalendarEvent;
import com.pinkpony.model.Rsvp;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;

/**
 * Created by neo on 23/3/16.
 */
@Projection(name = "inlineRsvp", types = Rsvp.class)
public interface RsvpProjection {

    String getName();
    String getResponse();
}