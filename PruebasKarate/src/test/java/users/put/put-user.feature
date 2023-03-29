Feature: Update User
  AS reqres admi
  I WANT TO be able to update the data of an existing user
  SO THAT I can keep updated information of registered users

  Scenario Outline: Update a User
    Given url "https://reqres.in/" + "api/users/" + <id>
    And request {"name": "#(name)","job": "#(job)"}
    When method put
    Then status <statusCode>
    And match $ == {"name": "#(name)", "job": "#(job)", "updatedAt": "#notnull"}

    Examples:
      | id | name   | job       | statusCode |
      | 1  | Jesus  | Tester    | 200        |
      | 2  | Miguel | Ingeniero | 200        |
      | 3  | Mafer  | Enfermera | 200        |