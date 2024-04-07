Feature: Calendar View of Upcoming Events

  Scenario: Viewing Upcoming Events in Calendar Format
    Given I am an event planner
    When I access the calendar view on the event planning platform
    Then I should see all upcoming events displayed in a calendar format
    

  Scenario: Viewing No Upcoming Events
    Given I am an event planner
    And there are no upcoming events
    When I access the calendar view on the event planning platform
    Then I should see a message indicating there are no upcoming events

  Scenario: Selecting an Event from the Calendar
    Given I am viewing upcoming events in calendar format
    When I select an event from the calendar
    Then I should see the details of the selected event
