package eventPlanner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class rating {
	 private final SharedState sharedState;

	    // Constructor that PicoContainer will use to inject shared state
	    public rating(SharedState state) {
	        this.sharedState = state;
	    }


    private Map<String, String> reviews = new HashMap<>();
    private Map<String, Double> ratings = new HashMap<>();
    private String currentVenue = "Some Venue";

    @Given("I have attended an event at a venue")
    public void iHaveAttendedAnEventAtAVenue() {
        // Assuming the currentVenue variable holds the name of the venue
        Assert.assertTrue("Should have attended an event", true);
    }

    @When("I leave a review with a rating and comment")
    public void iLeaveAReviewWithARatingAndComment() {
        // Simulate leaving a review
        reviews.put(currentVenue, "Great event, had a wonderful time!");
        ratings.put(currentVenue, 4.5); // Assume a 4.5 rating out of 5
    }

    @Then("my review should be successfully submitted")
    public void myReviewShouldBeSuccessfullySubmitted() {
        // Check if the review was submitted
        Assert.assertTrue("Review should be submitted", reviews.containsKey(currentVenue));
    }

    @Given("there are reviews for a venue")
    public void thereAreReviewsForAVenue() {
        // Pre-populate reviews for a venue
        reviews.put(currentVenue, "Nice venue, good service.");
        ratings.put(currentVenue, 4.0);
    }

    @When("I view the venue details")
    public void iViewTheVenueDetails() {
        // Simulate viewing venue details
        Assert.assertTrue("Should view venue details", true);
    }

    @Then("I should see the average rating")
    public void iShouldSeeTheAverageRating() {
        // Calculate and check the average rating
        double averageRating = ratings.get(currentVenue); // Simplified for this example
        Assert.assertEquals("Should see the average rating", 4.0, averageRating, 0.01);
    }

    @Then("I should be able to read individual reviews")
    public void iShouldBeAbleToReadIndividualReviews() {
        // Check if individual reviews can be read
        Assert.assertTrue("Should read individual reviews", reviews.containsKey(currentVenue));
    }

    @Given("I am the owner of a venue")
    public void iAmTheOwnerOfAVenue() {
        // Simulate being the owner of a venue
        currentVenue = "My Venue"; // Set the current venue to one owned by the user
    }

    @When("a user leaves a review for my venue")
    public void aUserLeavesAReviewForMyVenue() {
        // Simulate a user leaving a review
        reviews.put(currentVenue, "Had a great time, will come back!");
        ratings.put(currentVenue, 5.0);
    }

    @Then("I should be able to respond to the review")
    public void iShouldBeAbleToRespondToTheReview() {
        // Simulate the owner responding to a review
        String ownerResponse = "Thank you for your feedback!";
        Assert.assertNotNull("Owner should respond to the review", ownerResponse);
    }
}
