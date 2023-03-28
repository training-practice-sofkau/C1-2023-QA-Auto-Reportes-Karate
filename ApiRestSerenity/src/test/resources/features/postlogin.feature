Feature: login to the system
  AS reqres user
  I WANT TO login to the system
  THAT I can use the system services

  @Login
  Scenario Outline: login to the system
    Given the user is on the login page
    When the user send a login request with the email <email> and the password <password>
    Then user then sees a response with a status code <statusCode> and a token
    Examples:
      | email                      | password  | statusCode |
      | "eve.holt@reqres.in"       | "pass111" | 200        |
      | "janet.weaver@reqres.in"   | ""        | 400        |
      | ""                         | "pass333" | 400        |
      | "miguel.mendoza@gmail.com" | "pass444" | 400        |




