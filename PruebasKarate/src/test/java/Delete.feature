Feature: Delete an existing user
  As: Admin of REQRES API
  I want: To delete an existing user
  So that

  Background: Have access to REQRES API server
    * url 'https://reqres.in'

  @Delete
  Scenario Outline: Delete an existing user
    Given path 'api/users/<ID>'
    When method DELETE
    Then status <code>
    Examples:
      | ID | code |
      | 1  | 204  |
      | 0  | 204  |
      | -1 | 204  |
      |    | 204  |