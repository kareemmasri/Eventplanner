package eventPlanner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class search {

    private List<Event> allEvents = new ArrayList<>();
    private List<Event> filteredEvents = new ArrayList<>();

    // Assuming an Event class exists with appropriate fields and a constructor
    // For simplicity, we'll assume each event has a name, location, date, price, and reviewScore

    @Given("I am on the event search page")
    public void iAmOnTheEventSearchPage() {
        // Simulate being on the search page by initializing the events list
        allEvents.add(new Event("Event 1", "New York", "2024-05-20", 150, 4.5));
        allEvents.add(new Event("Event 2", "Los Angeles", "2024-06-15", 100, 4.7));
        // Add more events as needed for testing
    }

    @When("I search for events in {string}")
    public void iSearchForEventsIn(String location) {
        // Filter events by location
        filteredEvents = allEvents.stream()
                .filter(event -> event.getLocation().equalsIgnoreCase(location))
                .collect(Collectors.toList());
    }

    @Then("I should see a list of events located in {string}")
    public void iShouldSeeAListOfEventsLocatedIn(String location) {
        // Verify that filtered events are in the specified location
        boolean allMatchLocation = filteredEvents.stream()
                .allMatch(event -> event.getLocation().equalsIgnoreCase(location));
        Assert.assertTrue("All listed events should be in the specified location", allMatchLocation);
    }

    @Given("I have searched for events in {string}")
    public void iHaveSearchedForEventsIn(String location) {
        iSearchForEventsIn(location); // Reuse the search method
    }

    @When("I filter events by availability on {string}")
    public void iFilterEventsByAvailabilityOn(String date) {
        // Further filter the events by date
        filteredEvents = filteredEvents.stream()
                .filter(event -> event.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Then("I should see a list of events available on {string}")
    public void iShouldSeeAListOfEventsAvailableOn(String date) {
        // Verify that filtered events are available on the specified date
        boolean allMatchDate = filteredEvents.stream()
                .allMatch(event -> event.getDate().equals(date));
        Assert.assertTrue("All listed events should be available on the specified date", allMatchDate);
    }

    @When("I filter events by a maximum price of {string}")
    public void iFilterEventsByAMaximumPriceOf(String maxPriceStr) {
        double maxPrice = Double.parseDouble(maxPriceStr);
        filteredEvents = filteredEvents.stream()
                .filter(event -> event.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    @Then("I should see a list of events with ticket prices below {string}")
    public void iShouldSeeAListOfEventsWithTicketPricesBelow(String maxPriceStr) {
        double maxPrice = Double.parseDouble(maxPriceStr);
        boolean allMatchPrice = filteredEvents.stream()
                .allMatch(event -> event.getPrice() <= maxPrice);
        Assert.assertTrue("All listed events should have ticket prices below the specified maximum price", allMatchPrice);
    }

    @When("I sort events by highest reviews")
    public void iSortEventsByHighestReviews() {
        filteredEvents.sort((e1, e2) -> Double.compare(e2.getReviewScore(), e1.getReviewScore()));
    }

    @Then("I should see a list of events sorted by reviews from highest to lowest")
    public void iShouldSeeAListOfEventsSortedByReviewsFromHighestToLowest() {
        double previousReviewScore = Double.MAX_VALUE;
        for (Event event : filteredEvents) {
            Assert.assertTrue("Events should be sorted by reviews from highest to lowest",
                    event.getReviewScore() <= previousReviewScore);
            previousReviewScore = event.getReviewScore();
        }
    }

    @When("I filter by availability on {string}")
    public void iFilterByAvailabilityOn(String date) {
        iFilterEventsByAvailabilityOn(date); // Reuse the filter by availability method
    }

    @When("I filter by a maximum price of {string}")
    public void iFilterByAMaximumPriceOf(String maxPriceStr) {
        iFilterEventsByAMaximumPriceOf(maxPriceStr); // Reuse the filter by price method
    }

    @When("I sort by highest reviews")
    public void iSortByHighestReviews() {
        iSortEventsByHighestReviews(); // Reuse the sort by reviews method
    }

    @Then("I should see a list of events that match all selected criteria")
    public void iShouldSeeAListOfEventsThatMatchAllSelectedCriteria() {
        // No additional check needed here if the previous steps' assertions pass
    }

    // A basic Event class for the purpose of this example
    static class Event {
        private String name;
        private String location;
        private String date;
        private double price;
        private double reviewScore;

        public Event(String name, String location, String date, double price, double reviewScore) {
            this.name = name;
            this.location = location;
            this.date = date;
            this.price = price;
            this.reviewScore = reviewScore;
        }

        // Getters for Event class fields
        public String getLocation() { return location; }
        public String getDate() { return date; }
        public double getPrice() { return price; }
        public double getReviewScore() { return reviewScore; }
    }
}
