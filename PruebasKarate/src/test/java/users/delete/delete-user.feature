Feature: Delete User
  AS reqres admi
  I WANT TO be able to delete an existing user
  SO THAT I can keep the list of registered users updated

  Scenario Outline: Delete a User
    Given url "https://reqres.in/" + "api/users/" + <id>
    When method delete
    Then status <statusCode>

    Examples:
      | id | statusCode |
      | 1  | 204        |
      | 2  | 204        |
      | 3  | 204        |