Feature: Crear un usuario en reqres.in

  Scenario Outline: crear un usuario
    * url "https://reqres.in"
    * path "/api/users"
    * request {"name": "#(name)","job": "#(job)"}
    When method post
    Then status 201
    And match $.name == '#(name)'
    And match $.job == '#(job)'
    And match $.createdAt != null

    Examples:
    |name   |job    |
    |pedro  |QA     |
    |juan   |profe  |
    |Juan   |profe  |
    |JuAn   |profe  |
