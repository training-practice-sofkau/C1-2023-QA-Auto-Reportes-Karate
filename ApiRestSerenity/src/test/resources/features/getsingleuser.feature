Feature: List Users
  AS reqres user
  I WANT to generate a list of users
  SO THAT I can know who the registered users are

  @ListUser
  Scenario Outline: List Users
    Given the user is on the list page
    When the user sends a request with page number 2
    Then 
