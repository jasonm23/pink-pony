package com.pinkpony.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineRsvp", types = Rsvp.class)
public interface RsvpProjection {

    String getUsername();
    String getResponse();
}
