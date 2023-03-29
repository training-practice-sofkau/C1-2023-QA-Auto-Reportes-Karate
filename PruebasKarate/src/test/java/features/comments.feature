Feature: Actualizar datos de usuario en reqres
  AS  usuario de API de reqres
  I WANT TO
  actualizar datos de usuario
  SO THAT
  I actualizar datos
  Scenario Outline: Patch usuario
    Given   url "https://reqres.in"+"/api/users/" + <id>
    And request   {  "name": <name>,  "job": <job>  }
    When method patch
    Then status <code>
    And response.name != null
    And response.job != null
    And assert response.name != response.job
    Examples:
      | id | code | name   | job   |
      | 1  | 200  | "mary" | "dev" |
      | 3  | 200  | "juan" | "dev" |