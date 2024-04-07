Feature: Sign Up and Sign In Functionality

  Background: 
    Given the system is accessible via command in Eclipse

  Scenario: Successful Registration
    Given a visitor is on the command line interface
    When they register with a "Username", "Password", and "User Type"
    Then they should receive a message "Registration successful."

  Scenario: Registration with Existing Username
    Given "existinguser" is already registered
    When a visitor registers with "existinguser"
    Then they should receive a message "Registration failed - username already in use."

  Scenario: Successful Login
  Given "user1" is already registered with password "Password" and user type "UserType"
  When they login with "user1" and "Password"
  Then they should receive a message "Login successful."


  Scenario: Failed Login with Incorrect Password
    Given "user1" has an account
    When they attempt to login with "user1" and "WrongPassword"
    Then they should receive a message "Login failed - incorrect password."

  Scenario: Failed Login with Non-existent Username
    When a visitor attempts to login with "nonexistentuser" and "Password"
    Then they should receive a message "Login failed - account not found."
