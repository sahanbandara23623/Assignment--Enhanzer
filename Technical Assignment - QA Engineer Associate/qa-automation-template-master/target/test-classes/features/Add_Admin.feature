Feature: Automate Student Registration Form

  Background:
    Given I have opened the system

  Scenario: Valid form submission with all correct inputs
    And I wait few seconds
    And I wait few seconds
    And I click on "FirstName"
    And I generate a random UserName to the "FirstName"
    And I wait few seconds
    And I click on "Lastname"
    And I generate a random UserName to the "Lastname"
    And I wait few seconds
    And I click on "student_email"
    And I generate a random email address to the "student_email"
    And I click on "Gender"
    And I wait few seconds
    And I click on "Phone Number"
    And I generate a random mobile number to the "Phone Number"
    And I wait few seconds
    And I click on "Hobbies"
    And I wait few seconds
    Given user uploads the Image file "Subject"
    And I wait few seconds
    And I click on "Address"
    And I wait few seconds
    And I type "Automation Address" to the "Address"
    And I wait few seconds
    And I hard click "State" and press Enter
    And I wait few seconds
    And I hard click "City" and press Enter
    And I wait few seconds
    And I click on "Submit"
    And I wait few seconds
    And I wait few seconds

  Scenario: Form submission with mandatory fields left blank
    And I wait few seconds
    And I wait few seconds
    And I click on "student_email"
    And I generate a random email address to the "student_email"
    And I click on "Hobbies"
    And I wait few seconds
    And I wait few seconds
    And I click on "Address"
    And I wait few seconds
    And I type "Automation Address" to the "Address"
    And I wait few seconds
    And I click on "Submit"
    And I wait few seconds
    And I wait few seconds

  Scenario:  Invalid email format validation.
    And I wait few seconds
    And I wait few seconds
    And I click on "FirstName"
    And I generate a random UserName to the "FirstName"
    And I wait few seconds
    And I click on "Lastname"
    And I generate a random UserName to the "Lastname"
    And I wait few seconds
    And I click on "student_email"
    And I wait few seconds
    And I type "test#gmail.com" to the "student_email"
    And I click on "Gender"
    And I wait few seconds
    And I click on "Phone Number"
    And I generate a random mobile number to the "Phone Number"
    And I wait few seconds
    And I click on "Hobbies"
    And I wait few seconds
    And I wait few seconds
    And I click on "Address"
    And I wait few seconds
    And I type "Automation Address" to the "Address"
    And I wait few seconds
    And I click on "Submit"
    And I wait few seconds
    And I wait few seconds











