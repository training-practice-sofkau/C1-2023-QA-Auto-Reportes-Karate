Feature: Post usuario en reqres

  Background:
    * url "https://reqres.in"
    * path "/api/users/"
    * request { "name": "#(name)", "job": "#(job)" }

  Scenario Outline:Post un usuario
    When method post
    Then status 201
    Examples:
      | name    | job    |
      | Nevardo | QA     |
      | Antonio | QA     |
      | Luis    | Doctor |

