Feature: Get User

  Scenario: Get a user
    Given url "https://reqres.in" + "/api/users?page=" + "2"
    When method get
    Then status 200
