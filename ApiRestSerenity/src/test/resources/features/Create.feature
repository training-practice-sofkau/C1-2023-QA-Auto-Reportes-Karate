Feature: Create a new user
  As: Admin of REQRES API
  I want: To create a new user
  So that

  @Create
  Scenario Outline: Create a new user
    Given I have access to REQRES API server
    When I try to create a new user with '<JSON>' JSON
    Then I will see the status code <code>
    And I will receive a user ID '<ID>'
    Examples:
      | JSON    | code | ID       |
      | valid   | 201  | not null |
      | invalid | 400  | null     |