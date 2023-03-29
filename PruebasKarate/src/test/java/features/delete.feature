Feature: Delete User

  Scenario: Put a user
    Given url "https://reqres.in" + "/api/users/" + "1"
    And request { "name": "yeison", "job": "zion resident" }
    When method delete
    Then status 204
