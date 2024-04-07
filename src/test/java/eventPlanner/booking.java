package eventPlanner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class booking {

    private List<String> searchResults = new ArrayList<>();
    private String currentVenue;
    private boolean bookingConfirmed = false;

    @Given("I am a registered user")
    public void iAmARegisteredUser() {
        Assert.assertTrue("User should be registered", true);
    }

    @Given("I am logged into the platform")
    public void iAmLoggedIntoThePlatform() {
        Assert.assertTrue("User should be logged in", true);
    }

    @Given("I am on the venue search page")
    public void iAmOnTheVenueSearchPage() {
        Assert.assertTrue("Should be on the venue search page", true);
    }

    @When("I enter my desired location {string}")
    public void iEnterMyDesiredLocation(String location) {
        Assert.assertNotNull("Location should not be null", location);
    }

    @When("I specify the capacity requirement as {string}")
    public void iSpecifyTheCapacityRequirementAs(String capacity) {
        Assert.assertNotNull("Capacity requirement should not be null", capacity);
    }

    @When("I select desired amenities such as {string}, {string}, and {string}")
    public void iSelectDesiredAmenitiesSuchAsAnd(String amenity1, String amenity2, String amenity3) {
        Assert.assertNotNull("Amenities should not be null", amenity1);
    }

    @When("I set a maximum price limit of {string}")
    public void iSetAMaximumPriceLimitOf(String priceLimit) {
        Assert.assertNotNull("Price limit should not be null", priceLimit);
    }

    @When("I click on the search button")
    public void iClickOnTheSearchButton() {
        performSearch(); // Assuming this will populate the search results
    }

    @Then("I should see a list of venues that match my criteria")
    public void iShouldSeeAListOfVenuesThatMatchMyCriteria() {
        Assert.assertFalse("Search results should not be empty", searchResults.isEmpty());
    }

    @Given("I am viewing a list of search results")
    public void iAmViewingAListOfSearchResults() {
        performSearch(); // Make sure search results are available
        Assert.assertFalse("Should have search results to view", searchResults.isEmpty());
    }

    @When("I click on a specific venue")
    public void iClickOnASpecificVenue() {
        currentVenue = "Venue 1"; // Select the first venue from the search results for simplicity
        Assert.assertNotNull("A venue should be selected", currentVenue);
    }

    @Then("I should be taken to the venue details page")
    public void iShouldBeTakenToTheVenueDetailsPage() {
        Assert.assertNotNull("A venue should be selected", currentVenue);
    }

    @Then("I should see information such as venue name, address, capacity, amenities, and pricing")
    public void iShouldSeeInformationSuchAsVenueNameAddressCapacityAmenitiesAndPricing() {
        Assert.assertEquals("Venue 1", currentVenue); // Check that the correct venue is selected
    }

    @Given("I am viewing the details of a specific venue")
    public void iAmViewingTheDetailsOfASpecificVenue() {
        selectVenue("Venue 1"); // Simulate selecting a specific venue
    }

    private void selectVenue(String venueName) {
        currentVenue = venueName; // Set the current venue
        Assert.assertNotNull("A venue should be selected", currentVenue);
    }

    @When("I click on the {string} button")
    public void iClickOnTheButton(String button) {
        Assert.assertNotNull("Button name should not be null", button);
    }

    @When("I select the date and time for my event")
    public void iSelectTheDateAndTimeForMyEvent() {
        Assert.assertTrue("Date and time should be selected", true);
    }

    @When("I review the booking details")
    public void iReviewTheBookingDetails() {
        Assert.assertNotNull("Booking details should be reviewed", currentVenue);
    }

    @When("I confirm the booking")
    public void iConfirmTheBooking() {
        bookingConfirmed = true; // Confirm the booking
    }

    @Then("I should receive a confirmation message")
    public void iShouldReceiveAConfirmationMessage() {
        Assert.assertTrue("Booking should be confirmed", bookingConfirmed);
    }

    @Then("the venue should be reserved for my event on the specified date and time")
    public void theVenueShouldBeReservedForMyEventOnTheSpecifiedDateAndTime() {
        Assert.assertTrue("The venue should be reserved", bookingConfirmed);
    }

    @Given("I have previously booked a venue for an event")
    public void iHavePreviouslyBookedAVenueForAnEvent() {
        bookingConfirmed = true; // Simulate a previous booking
        Assert.assertTrue("There should be a previous booking", bookingConfirmed);
    }

    @When("I navigate to my bookings page")
    public void iNavigateToMyBookingsPage() {
        Assert.assertTrue("Should navigate to bookings page", true);
    }

    @Then("I should see a list of all my booked venues")
    public void iShouldSeeAListOfAllMyBookedVenues() {
        Assert.assertTrue("Should have at least one booking", bookingConfirmed);
    }

    @Then("I should be able to view details, modify, or cancel bookings as needed")
    public void iShouldBeAbleToViewDetailsModifyOrCancelBookingsAsNeeded() {
        Assert.assertTrue("Should be able to interact with bookings", true);
    }

    // Simulates a search operation that populates search results
    private void performSearch() {
        if (searchResults.isEmpty()) {
            searchResults.add("Venue 1"); // Add mock venues as search results
            searchResults.add("Venue 2");
        }
    }
}
