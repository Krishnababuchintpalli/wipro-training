Feature: Admin Login and Create Customer

  Scenario: Login to Bagisto, Create a New Customer, and Logout
    Given User launches Chrome browser
    And User maximizes the browser window
    And User deletes all cookies
    And User navigates to the Bagisto admin login page
    And User waits for login page to load
    When User clears email and enters admin email as "admin@example.com"
    And User clears password and enters admin password as "admin123"
    And User clicks on the login button
    Then User should be redirected to admin dashboard

    When User clicks on Customers in the sidebar
    Then User should be redirected to the Customers page
    When User clicks on Create Customer button
    And User enters customer details
    And User clicks on Save Customer button
    Then New customer should be created successfully

    And User clicks on profile button
    And User clicks on logout link
    Then User should be logged out successfully

    And User closes the browser