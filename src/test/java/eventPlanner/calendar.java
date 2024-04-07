package eventPlanner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class calendar {

    private List<Event> upcomingEvents = new ArrayList<>();
    private Event selectedEvent = null;
    private boolean shouldDisplayEvents = true; // New flag to control event display

    @Given("I am an event planner")
    public void iAmAnEventPlanner() {
        // Assume the user is authenticated as an event planner
    }
  
    @When("I access the calendar view on the event planning platform")
    public void iAccessTheCalendarViewOnTheEventPlanningPlatform() {
        if (shouldDisplayEvents) {
            upcomingEvents.add(new Event("Event 1", "2024-01-15"));
            upcomingEvents.add(new Event("Event 2", "2024-02-20"));
        }
    }

    @Then("I should see all upcoming events displayed in a calendar format")
    public void iShouldSeeAllUpcomingEventsDisplayedInACalendarFormat() {
        Assert.assertFalse("There should be upcoming events displayed in a calendar format", upcomingEvents.isEmpty());
        System.out.println("Upcoming Events:");
        for (Event event : upcomingEvents) {
            System.out.println(event.getName() + " - " + event.getDate());
        }
    }
    
    @Given("there are no upcoming events")
    public void thereAreNoUpcomingEvents() {
        upcomingEvents.clear(); // Ensure the list is cleared
        shouldDisplayEvents = false; // Ensure no events will be added
    }

    @Then("I should see a message indicating there are no upcoming events")
    public void iShouldSeeAMessageIndicatingThereAreNoUpcomingEvents() {
        Assert.assertTrue("There should be no upcoming events", upcomingEvents.isEmpty());
        System.out.println("No upcoming events available.");
    }

    @Given("I am viewing upcoming events in calendar format")
    public void iAmViewingUpcomingEventsInCalendarFormat() {
        shouldDisplayEvents = true; // Reset flag to display events for other scenarios
        iAccessTheCalendarViewOnTheEventPlanningPlatform(); // Populate the list of upcoming events
    }

    @When("I select an event from the calendar")
    public void iSelectAnEventFromTheCalendar() {
        if (!upcomingEvents.isEmpty()) {
            selectedEvent = upcomingEvents.get(0); // Select the first event
        }
    }

    @Then("I should see the details of the selected event")
    public void iShouldSeeTheDetailsOfTheSelectedEvent() {
        Assert.assertNotNull("An event should be selected", selectedEvent);
        System.out.println("Selected Event Details:");
        System.out.println("Name: " + selectedEvent.getName());
        System.out.println("Date: " + selectedEvent.getDate());
    }

    static class Event {
        private String name;
        private String date;

        Event(String name, String date) {
            this.name = name;
            this.date = date;
        }

        String getName() {
            return name;
        }

        String getDate() {
            return date;
        }
    }
}
