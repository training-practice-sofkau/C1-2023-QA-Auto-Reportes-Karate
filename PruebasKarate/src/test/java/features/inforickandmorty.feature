Feature: Information Rick and Morty
  AS API USER
  I WANT TO
  make  a request GET
  SO THAT
  i can see information about rick and morty
  Scenario: Resquest sucessful
    Given url "https://rickandmortyapi.com/api"
    When method Get
    Then status 200
    And match response == {"characters": "https://rickandmortyapi.com/api/character","locations": "https://rickandmortyapi.com/api/location","episodes": "https://rickandmortyapi.com/api/episode"}