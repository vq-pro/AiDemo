Feature: SpringDemo

  Scenario: Main Page

    Given some items
    When the page is displayed
    Then we are on the main page
    And we see the items
