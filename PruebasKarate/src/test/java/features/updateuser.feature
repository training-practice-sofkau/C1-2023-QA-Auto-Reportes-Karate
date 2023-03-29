Feature: Update User

  Scenario: Put a user
    Given url "https://reqres.in" + "/api/users/" + "2"
    And request { "name": "yeison", "job": "zion resident" }
    When method put
    Then status 200
    And match $.name == 'yeison'

