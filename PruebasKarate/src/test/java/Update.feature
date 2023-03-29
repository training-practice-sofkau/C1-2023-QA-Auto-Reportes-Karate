Feature: Update an existing user
  As: Admin of REQRES API
  I want: To update an existing user
  So that

  Background: Have access to REQRES API server
    * url 'https://reqres.in'

  @Update
  Scenario Outline: Update an existing user
    Given path 'api/users/<ID>'
    And request {"name": "<name>", "job": "<job>"}
    When method PUT
    Then status <code>
    And match $ == {"name": "<name>", "job": "<job>", "updatedAt": "#notnull"}
    Examples:
      | ID | name     | job           | code |
      | 1  | morpheus | zion resident | 200  |
      | 0  | neo      | hacker        | 200  |
      | -1 | trinity  | fighter       | 200  |