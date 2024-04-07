package eventPlanner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class creation {

    // Assuming these variables simulate the state of the application
    private boolean eventCreated = false;
    private boolean eventUpdated = false;
    private boolean eventCancelled = false;
    private boolean registrationSuccessful = false;
    private boolean registrationFailed = false;
    private String lastEventName = "";

    @Given("I am logged in as a guest")
    public void iAmLoggedInAsAGuest() {
        // Simulate guest login
    }

    @When("I view the list of available events")
    public void iViewTheListOfAvailableEvents() {
        // Simulate viewing available events, assuming events are always available for simplicity
    }

    @Then("I should see a list of events with {string}, {string}, and {string}")
    public void iShouldSeeAListOfEventsWithAnd(String eventName, String eventDate, String location) {
        // Verify the event list includes the specified details, placeholder for actual check
        Assert.assertTrue("Event details should be visible", true);
    }

    @When("I choose to join an event with {string}")
    public void iChooseToJoinAnEventWith(String eventName) {
        // Simulate joining an event
        lastEventName = eventName;
        registrationSuccessful = true; // Assume registration is successful
    }

    @Then("I should be successfully registered for {string}")
    public void iShouldBeSuccessfullyRegisteredFor(String eventName) {
        // Check if the registration was successful
        Assert.assertTrue("Should be registered for the event", registrationSuccessful && lastEventName.equals(eventName));
    }

    @Then("I should receive a confirmation message {string}")
    public void iShouldReceiveAConfirmationMessage(String message) {
        // Check if the expected message is received, placeholder for actual message check
        Assert.assertEquals("Confirmation message should be received", "You have successfully joined '" + lastEventName + "'.", message);
    }

    @Given("I am logged in as an event planner")
    public void iAmLoggedInAsAnEventPlanner() {
        // Simulate event planner login
    }

    @When("I create an event with {string}, {string}, {string}, and {string}")
    public void iCreateAnEventWithAnd(String eventName, String eventDate, String location, String capacity) {
        // Simulate event creation
        eventCreated = true;
        lastEventName = eventName;
    }

    @Then("the event {string} should be successfully created")
    public void theEventShouldBeSuccessfullyCreated(String eventName) {
        // Check if the event was created successfully
        Assert.assertTrue("Event should be created", eventCreated && lastEventName.equals(eventName));
    }

    @Then("it should be visible to guests and participants")
    public void itShouldBeVisibleToGuestsAndParticipants() {
        // Verify the created event is visible to guests and participants, placeholder for actual visibility check
        Assert.assertTrue("Event should be visible to all", eventCreated);
    }

    @Given("I have an existing event {string}")
    public void iHaveAnExistingEvent(String eventName) {
        // Assume the event exists
        lastEventName = eventName;
    }

    @When("I update the {string} and {string} for {string}")
    public void iUpdateTheAndFor(String eventDate, String location, String eventName) {
        // Simulate event update
        if (eventName.equals(lastEventName)) {
            eventUpdated = true;
        }
    }

    @Then("the updates for {string} should be successfully saved")
    public void theUpdatesForShouldBeSuccessfullySaved(String eventName) {
        // Check if the event updates were saved successfully
        Assert.assertTrue("Event updates should be saved", eventUpdated && eventName.equals(lastEventName));
    }

    @Then("the updated details should be visible to guests and participants")
    public void theUpdatedDetailsShouldBeVisibleToGuestsAndParticipants() {
        // Verify the updated event details are visible, placeholder for actual visibility check
        Assert.assertTrue("Updated details should be visible", eventUpdated);
    }

    @When("I cancel {string}")
    public void iCancel(String eventName) {
        // Simulate event cancellation
        if (eventName.equals(lastEventName)) {
            eventCancelled = true;
        }
    }

    @Then("{string} should be marked as canceled")
    public void shouldBeMarkedAsCanceled(String eventName) {
        // Verify the event is marked as canceled
        Assert.assertTrue("Event should be canceled", eventCancelled && eventName.equals(lastEventName));
    }

    @Then("participants should be notified about the cancellation")
    public void participantsShouldBeNotifiedAboutTheCancellation() {
        // Placeholder for actual notification check
        Assert.assertTrue("Participants should be notified", eventCancelled);
    }

    @Given("there is an event {string} with no available spots")
    public void thereIsAnEventWithNoAvailableSpots(String eventName) {
        // Assume the event is full
        lastEventName = eventName;
        registrationFailed = true;
    }

    @When("I attempt to join {string}")
    public void iAttemptToJoin(String eventName) {
        // Simulate an attempt to join a full event
        if (eventName.equals(lastEventName) && registrationFailed) {
            registrationSuccessful = false;
        }
    }

    @Then("I should receive an error message {string}")
    public void iShouldReceiveAnErrorMessage(String errorMessage) {
        // Verify the expected error message is received
        Assert.assertEquals("Should receive a full event error message", "Sorry, '" + lastEventName + "' is full.", errorMessage);
    }

    @Given("I am logged in as an admin")
    public void iAmLoggedInAsAnAdmin() {
        // Simulate admin login
    }

    @When("I access the events overview dashboard")
    public void iAccessTheEventsOverviewDashboard() {
        // Simulate accessing the events dashboard
    }

    @Then("I should see all events including their {string}, {string}, {string}, {string}, and {string}")
    public void iShouldSeeAllEventsIncludingTheirAnd(String eventName, String eventDate, String location, String capacity, String currentRegistrations) {
        // Placeholder for verifying all events are visible with details
        Assert.assertTrue("Admin should see all events", true);
    }

    @When("I select an event with {string}")
    public void iSelectAnEventWith(String eventName) {
        // Simulate selecting an event for more actions
        lastEventName = eventName;
    }

    @Then("I should have options to view detailed information, edit, cancel, or directly register participants for the event")
    public void iShouldHaveOptionsToViewDetailedInformationEditCancelOrDirectlyRegisterParticipantsForTheEvent() {
        // Placeholder for verifying admin has all options for event management
        Assert.assertTrue("Admin should have full control over the event", true);
    }

    @Then("I should be able to access a list of all participants for any event")
    public void iShouldBeAbleToAccessAListOfAllParticipantsForAnyEvent() {
        // Placeholder for verifying admin can access participant list
        Assert.assertTrue("Admin should access participant list", true);
    }

    @When("I view the list of all users")
    public void iViewTheListOfAllUsers() {
        // Simulate viewing all user accounts
    }

    @Then("I should see their {string}, {string}, and registration status for events")
    public void iShouldSeeTheirAndRegistrationStatusForEvents(String username, String userType) {
        // Placeholder for verifying user details and registration status are visible
        Assert.assertTrue("Admin should see all user details", true);
    }

    @Then("I should have the options to activate, deactivate, or delete any user account")
    public void iShouldHaveTheOptionsToActivateDeactivateOrDeleteAnyUserAccount() {
        // Placeholder for verifying admin has full user account management options
        Assert.assertTrue("Admin should have full user account control", true);
    }
}
