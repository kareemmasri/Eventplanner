Feature: Event Hall Decor Customization by Event Planner

  Scenario: Offering Decoration Packages
    When the event planner presents the decoration options
    Then guests should be provided with a variety of packages to choose from

  Scenario: Customizing Decoration Details
    Given a guest has selected a decoration package
    When the event planner discusses customization options with the guest
    And agrees on details such as colors, themes, and floral arrangements
    Then the event planner should be able to customize the package as per the guest's preferences
    And confirm the final decoration details for the event
