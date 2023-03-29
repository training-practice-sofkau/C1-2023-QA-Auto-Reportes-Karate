Feature: eliminar un usuario en reqres.in

  Scenario Outline: eliminar un usuario
    Given url "https://reqres.in"+"/api/users/"+'#(id)'
    When method delete
    Then status 204
    And match "'"+204+"'" == '#(code)'
    Examples:
      | id | code  |
      | 1  | '204' |
      | 2  | '204' |
      | -2 | '400' |
