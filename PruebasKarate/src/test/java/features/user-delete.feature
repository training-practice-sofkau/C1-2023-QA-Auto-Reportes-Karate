Feature: Delete usuario reqres

  Scenario:Delete
    Given url "https://reqres.in" + "/api/users/" + "1"
    When method DELETE
    Then status 204
