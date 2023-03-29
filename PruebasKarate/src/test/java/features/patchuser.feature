Feature: Patch User

  @Put
  Scenario: Put a user
    Given url "https://reqres.in" + "/api/users/" + "2"
    And request { "name": "yeison", "job": "zion resident" }
    When method Patch
    Then status 200
    And match $.name == 'yeison'

