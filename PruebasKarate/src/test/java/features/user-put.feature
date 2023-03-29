Feature: PUT usuario reqres

  Scenario:Put user
    Given url "https://reqres.in" + "/api/users/" + "2"
    And request { "name": "Antonio", "job": "zion resident" }
    When method PUT
    Then status 200
    And match $.name == 'Antonio'
