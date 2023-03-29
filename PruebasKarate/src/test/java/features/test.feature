Feature: plan de pruebas api REST

  Scenario Outline: casos de prueba <nombreCaso2>
    Given url 'https://reqres.in/api/users'
    When request {"<name>": <Vname>,"<job>": <Vjob>}
    And method POST
    Then status 201
    Examples:
      | nombreCaso2    | name | Vname    | job | Vjob  |
      | name vacio     | name |          | job | "QA"  |
      | name nulo      | name | null     | job | "QA"  |
      | name 3 letras  | name | "sol"    | job | "QA"  |
      | sin campo name |      |          | job | "QA"  |
      | job vacio      | name | "jeremy" | job | ""    |
      | job nulo       | name | "jeremy" | job | null  |
      | job 3 letras   | name | "jeremy" | job | "QAs" |
      | sin campo job  | name | "jeremy" |     | ""    |

