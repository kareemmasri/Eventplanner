Feature: Event Participation and Management

  Scenario: Guest Views and Joins Available Events
    Given I am logged in as a guest
    When I view the list of available events
    Then I should see a list of events with "Event Name", "Event Date", and "Location"
    When I choose to join an event with "Event Name"
    Then I should be successfully registered for "Event Name"
    And I should receive a confirmation message "You have successfully joined 'Event Name'."

  Scenario: Event Planner Creates a New Event
    Given I am logged in as an event planner
    When I create an event with "Event Name", "Event Date", "Location", and "Capacity"
    Then the event "Event Name" should be successfully created
    And it should be visible to guests and participants
  Scenario: Event Planner Updates an Event
    Given I am logged in as an event planner
    And I have an existing event "Event Name"
    When I update the "Event Date" and "Location" for "Event Name"
    Then the updates for "Event Name" should be successfully saved
    And the updated details should be visible to guests and participants

  Scenario: Event Planner Cancels an Event
    Given I am logged in as an event planner
    And I have an existing event "Event Name"
    When I cancel "Event Name"
    Then "Event Name" should be marked as canceled
    And participants should be notified about the cancellation

  Scenario: Guest Tries to Join a Full Event
    Given I am logged in as a guest
    And there is an event "Event Name" with no available spots
    When I attempt to join "Event Name"
    Then I should receive an error message "Sorry, 'Event Name' is full."
    
  Scenario: Admin Overviews and Manages All Events
    Given I am logged in as an admin
    When I access the events overview dashboard
    Then I should see all events including their "Event Name", "Event Date", "Location", "Capacity", and "Current Registrations"
    When I select an event with "Event Name"
    Then I should have options to view detailed information, edit, cancel, or directly register participants for the event
    And I should be able to access a list of all participants for any event
    When I view the list of all users
    Then I should see their "Username", "User Type", and registration status for events
    And I should have the options to activate, deactivate, or delete any user account
    