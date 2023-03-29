Feature: Delete usuario reqres

  Scenario:Delete
    Given url "https://reqres.in" + "/api/users/" + ""
    When method DELETE
    Then status 204
