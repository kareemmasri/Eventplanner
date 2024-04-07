Feature: Venue Management and Booking

  As an event planner
  I want to be able to search for venues based on various criteria
  So that I can find the perfect location for my event and book it seamlessly

  Background:
    Given I am a registered user
    And I am logged into the platform

  Scenario: Search for venues
    Given I am on the venue search page
    When I enter my desired location "New York City"
    And I specify the capacity requirement as "100-200 people"
    And I select desired amenities such as "parking", "WiFi", and "catering"
    And I set a maximum price limit of "$5000"
    And I click on the search button
    Then I should see a list of venues that match my criteria

  Scenario: View venue details
    Given I am viewing a list of search results
    When I click on a specific venue
    Then I should be taken to the venue details page
    And I should see information such as venue name, address, capacity, amenities, and pricing

  Scenario: Book a venue
    Given I am viewing the details of a specific venue
    When I click on the "Book Now" button
    And I select the date and time for my event
    And I review the booking details
    And I confirm the booking
    Then I should receive a confirmation message
    And the venue should be reserved for my event on the specified date and time

  Scenario: Manage venue bookings
    Given I have previously booked a venue for an event
    When I navigate to my bookings page
    Then I should see a list of all my booked venues
    And I should be able to view details, modify, or cancel bookings as needed
