Feature: Rest Server

  Scenario: Add an item

    When we add an item
    Then the item has been added to the DB

  Scenario: Get a single item

    Given an item in the DB
    When we request that item
    Then we get the same item

  Scenario: Get a list of items

    Given some items in the DB
    When we request the items
    Then we get an array with the same items

  Scenario: Delete an item

    Given an item in the DB
    When we delete the item
    Then the item is gone from the DB

  Scenario: Update an item

    Given an item in the DB
    When we update the item
    Then the item has been updated in the DB
