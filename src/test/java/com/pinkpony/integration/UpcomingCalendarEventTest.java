package com.pinkpony.integration;

import com.jayway.restassured.http.ContentType;
import com.pinkpony.model.CalendarEvent;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class UpcomingCalendarEventTest extends PinkPonyIntegrationBase {

    private DateTime today = new DateTime(DateTimeZone.forID("UTC"));
    private DateTime yesterday = today.minusDays(1);
    private DateTime tomorrow = today.plusDays(1);
    private DateTime nextWeek = today.plusWeeks(1);

    private CalendarEvent pastEvent;
    private CalendarEvent futureEventTmr;
    private CalendarEvent futureCancelledEvent;
    private CalendarEvent futureEventNextWeek;

    private String upcomingUrl = "/calendarEvents/search/upcomingEvents";

    /*
     * An upcoming event is one that:
     * - is in the future AND
     * - is not cancelled
     *
     * Upcoming events must be returned soonest first.
     */
    @Test
    public void getUpcomingEvents() {
        setupUpcomingEvents();

        given().
            contentType(ContentType.JSON).
        when().
            get(upcomingUrl).
        then().
            statusCode(200).
            body("_embedded.calendarEvents", hasSize(2)).
            body("_embedded.calendarEvents[0].name", equalTo("future event")).
            body("_embedded.calendarEvents[0].calendarEventDateTime", equalTo(dateFormat.format(tomorrow.toDate()))).
            body("_embedded.calendarEvents[1].name", equalTo("future event next week")).
            body("_embedded.calendarEvents[1].calendarEventDateTime", equalTo(dateFormat.format(nextWeek.toDate())));
    }

    @Test
    public void getUpcomingEventsShowsMessageViaProjection() {
        String upcomingProjectionUrl = "/calendarEvents/upcomingMessage";
        setupUpcomingEvents();

        given().
            contentType(ContentType.JSON).
        when().
            get(upcomingProjectionUrl).
        then().
            statusCode(200).
            body("content", equalTo(String.format("%s%s", futureEventTmr.showMessage(), futureEventNextWeek.showMessage())));
    }

    private void setupUpcomingEvents() {
        calendarEventRepository.deleteAll();

        pastEvent = makeCalendarEvent(yesterday.toDate());
        pastEvent.setName("past event");
        futureEventTmr = makeCalendarEvent(tomorrow.toDate());
        futureEventTmr.setName("future event");
        futureCancelledEvent = makeCalendarEvent(tomorrow.toDate());
        futureCancelledEvent.setName("future cancelled event");
        futureCancelledEvent.setCancelled(true);
        futureEventNextWeek = makeCalendarEvent(nextWeek.toDate());
        futureEventNextWeek.setName("future event next week");

        calendarEventRepository.save(pastEvent);
        calendarEventRepository.save(futureEventNextWeek);
        calendarEventRepository.save(futureEventTmr);
        calendarEventRepository.save(futureCancelledEvent);
    }
}
