Feature: List a user
  AS reqres admi
  I WANT to generate a list of users
  SO THAT I can know who the registered users are

  @ListUser
  Scenario Outline: List a user
    Given the admi is on the list page
    When the user makes a request with the user id <id>
    Then the user should see a response containing the user id <id>, email  <email>, name <name>, last name <lastName> and status code <statusCode>
    Examples:
      | id | email                    | name     | lastName | statusCode |
      | 1  | "george.bluth@reqres.in" | "George" | "Bluth"  | 200        |
      | 2  | "janet.weaver@reqres.in" | "Janet"  | "Weaver" | 200        |
      | 3  | "emma.wong@reqres.in"    | "Emma"   | "Wong"   | 200        |

