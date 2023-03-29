Feature: Delete User
  As a user of ReqRes API
  I want to delete an existing user
  So that I can remove outdated data from the ReqRes API

  @DeleteUser
  Scenario: Delete an existing user in ReqRes API
    Given url "https://reqres.in" + "/api/users/" + "2"
    When method delete
    Then status 204


