Feature: Create User
  AS a reqres user
  I WANT TO make a request to the service
  SO THAT I CAN enter a new user information

  Background:
    Given the user is in the reqres web page

  Scenario Outline: Create user successfully
    When the user sends a request with the user's name "<name>" and job "<job>"
    Then the user gets a status code response Created, and can see the user's id, name "<name>", job "<job>" and creation time
    Examples:
      | name    | job       |
      | Andres  | QA        |
      | Juliana | Developer |
      | Mario   | PO        |

    Scenario: Empty user creation
      When the user sends an empty request
      Then  the user gets a status code Created, an can see the user's id and creation time