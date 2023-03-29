Feature: Post request

  Background:
    * url 'https://reqres.in/api'
    * header Accept = 'application/json'

  Scenario: Post scenario normal
    Given url 'https://reqres.in/api/users'
    And request { "name": "Raghav", "job": "leader"}
    When method post
    Then status 201

  Scenario: Post con assertion
    Given path '/users'
    And request { "name": "Raghav", "job": "leader"}
    When method post
    Then status 201
    And match response == {"name": "Raghav", "job": "leader","id": "#string","createdAt": "#ignore"}


