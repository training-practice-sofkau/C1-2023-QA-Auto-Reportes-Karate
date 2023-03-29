Feature: Update User

  Scenario Outline: Actualizar usuario con diferentes nombres y "trabajos
    Given url "https://reqres.in/api/users/<id>"
    And request { "name": <name>, "job": <job> }
    When method put
    Then status 200
    And match response.name == name

    Examples:
      | id | name    | job             |
      | 2  | Alice   | Developer       |
      | 2  | Bob     | Designer        |
      | 3  | Charlie | Project Manager |
