Feature: Event Search and Filtering

  Scenario: Searching events by location
    Given I am on the event search page
    When I search for events in "New York"
    Then I should see a list of events located in "New York"

  Scenario: Filtering events by availability
    Given I am on the event search page
    And I have searched for events in "Los Angeles"
    When I filter events by availability on "2024-06-20"
    Then I should see a list of events available on "2024-06-20"

  Scenario: Filtering events by pricing
    Given I am on the event search page
    And I have searched for events in "Chicago"
    When I filter events by a maximum price of "200"
    Then I should see a list of events with ticket prices below "200"

  Scenario: Sorting events by reviews
    Given I am on the event search page
    And I have searched for events in "Miami"
    When I sort events by highest reviews
    Then I should see a list of events sorted by reviews from highest to lowest

  Scenario: Filtering events by multiple criteria
    Given I am on the event search page
    When I search for events in "San Francisco"
    And I filter by availability on "2024-09-15"
    And I filter by a maximum price of "300"
    And I sort by highest reviews
    Then I should see a list of events that match all selected criteria
