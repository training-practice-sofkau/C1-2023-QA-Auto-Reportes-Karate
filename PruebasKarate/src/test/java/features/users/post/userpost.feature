Feature: Create employee on reqres
  AS
  reqres admin
  I WANT TO
  create a employee in the system
  SO THAT
  I want to keep the database updated with the information of the employees

  @CreateEmployee
  Scenario Outline:
    Given url "https://reqres.in" + "/api/users/"
    And request { "name": "#(name)", "job": "#(job)" }
    When method post
    Then status <statuscode>
    And match $ == { "name": "#(name)", "job": "#(job)", "id": "#string", "createdAt": "#notnull"}

    Examples:
      | name    | job    | statuscode |
      | Gretty  | QA     | 201        |
      | Adriana | leader | 201        |
      | Jairo   | leader | 201        |
      | Jorge   | dev    | 201        |