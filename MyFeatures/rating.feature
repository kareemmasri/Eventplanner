Feature: Venue Review and Rating System

  Scenario: Leave a review for a venue
    Given I have attended an event at a venue
    When I leave a review with a rating and comment
    Then my review should be successfully submitted

  Scenario: View average ratings and reviews for a venue
    Given there are reviews for a venue
    When I view the venue details
    Then I should see the average rating
    And I should be able to read individual reviews

  Scenario: Venue owner responds to a review
    Given I am the owner of a venue
    When a user leaves a review for my venue
    Then I should be able to respond to the review
