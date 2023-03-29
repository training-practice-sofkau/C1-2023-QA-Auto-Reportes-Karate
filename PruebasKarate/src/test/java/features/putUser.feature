Feature: Update User
  As a user of ReqRes API
  I want to update an existing user's information
  So that I can ensure the accuracy of the data in the ReqRes API

  @UpdateUser
  Scenario Outline: Update an existing user's information in ReqRes API
    Given url "https://reqres.in" + "/api/users/" + "2"
    And request {"name": <name>,"job": <job>}
    When method put
    Then status 200
    And match $.updatedAt != null

    Examples:
      | name      | job        |
      | "Jessica" | "Engineer" |
      | ""        | ""         |
      | "  "      | "*/"       |






