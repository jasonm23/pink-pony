package com.pinkpony.validator;

import com.pinkpony.model.CalendarEvent;
import com.pinkpony.model.Rsvp;
import org.joda.time.DateTime;
import org.springframework.validation.Errors;

import java.util.Date;

public class RsvpUpdateValidator extends RsvpBaseValidator {

    @Override
    public void validate(Object object, Errors errors) {
        commonValidate(object, errors);

        Rsvp rsvp = (Rsvp) object;
        CalendarEvent calendarEvent = rsvp.calendarEvent;
        Date timeNow = new DateTime().toDate();
        if ( calendarEvent != null && timeNow.compareTo(calendarEvent.getCalendarEventDateTime()) > 0 ) {
            errors.rejectValue("calendarEvent", "rsvp.calendarEvent.field.updateInThePast");
        }
    }
}
