Feature: Customers

  Background: Common steps for each scenario
    Given user launch chrome browser
    When user opens url "https://admin-demo.nopcommerce.com/login"
    And user enters email as "admin@yourstore.com" and password as "admin"
    And click on login
    Then user can view dashboard

@Sanity
  Scenario: Add a new customer
    When user click on customers menu
    And click on customers menu item
    And click on add new button
    Then user can view Add New Customer page
    When user enter customer info
    And click on save button
    Then user can view confirmation message "The new customer has been added successfully"
    And close browser

@Regression
  Scenario: Search customer by EmailID
    When user click on customers menu
    And click on customers menu item
    And enter customer email
    When click on search button
    Then user should found email in search table
    And close browser

@Regression
  Scenario: Search customer by name
    When user click on customers menu
    And click on customers menu item
    And enter customer firstname
    And enter customer lastname
    When click on search button
    Then user should found name in search table
    And close browser
