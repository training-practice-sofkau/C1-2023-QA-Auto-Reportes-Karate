Feature: Create Authentication Token
  As a user of Booking API
  I want to create a new authentication token with my credentials
  So that I can access for update and delete requests in Booking API

  @CreateAuthToken
  Scenario Outline: Create a new authentication token for update and delete requests in Booking API
    Given I am in the Booking API
    When I make a POST request to create a new authentication token with valid username <username> and password <password> credentials
    Then the status code should be <code>
    And the response body should contain a valid authentication token
    Examples:
      | username | password       | code |
      | "admin"  | "password123"  | 200  |
      | ""       | "password123"  | 200  |
      | ""       | ""             | 200  |
      | "admin"  | "\n"           | 200  |
      | "admin " | "password123"  | 200  |
      | "admin"  | " password123" | 200  |