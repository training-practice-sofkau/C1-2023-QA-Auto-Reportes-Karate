Feature: Delete User

  Scenario Outline: Delete usuario con diferentes IDs y parámetros
    Given url "https://reqres.in/api/users/<id>"
    When method delete
    Then status 204

    Examples:
      | id |
      | 1  |
      | 2  |
      | 3  |

