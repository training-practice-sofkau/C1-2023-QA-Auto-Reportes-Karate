Feature: Delete User
  As a user of ReqRes API
  I want to delete an existing user
  So that I can remove outdated data from the ReqRes API

  @DeleteUser
  Scenario Outline: Delete an existing user in ReqRes API
    Given url "https://reqres.in" + "/api/users/" + <id>
    When method delete
    Then status 204

    Examples:
      | id   |
      | "1"  |
      | "2"  |
      | ""   |
      | "-1" |


