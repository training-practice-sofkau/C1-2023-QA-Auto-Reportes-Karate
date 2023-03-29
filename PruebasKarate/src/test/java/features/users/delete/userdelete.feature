Feature: Delete employee on reqres
  AS
  reqres admin
  I WANT TO
  delete a employee in the system
  SO THAT
  I want to delete an existing contact in the employee database

  @DeleteEmployee
  Scenario Outline:
    Given url "https://reqres.in" + "/api/users/" + <id>
    When method delete
    Then status <statuscode>

    Examples:
      | id | statuscode |
      | 5  | 204        |
      | 6  | 204        |
      | 7  | 204        |