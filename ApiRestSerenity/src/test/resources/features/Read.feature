Feature: Read user info
  As: Admin of REQRES API
  I want: To read users info
  So that

  @Read
  Scenario Outline: Read some users info
    Given I have access to the REQRES API server
    When I try to read user '<ID>' info
    Then I will see a status code <code>
    And I will receive a JSON '<JSON>' with user '<ID>' info
    Examples:
      | ID | code | JSON      |
      | 1  | 200  | not empty |
      | 0  | 404  | empty     |
      | -1 | 404  | empty     |