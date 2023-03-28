Feature: Get Successful
  AS  JSON place holder user
  I WANT TO do a request to the system
  SO THAT I can see the page posts

  @Get
  Scenario Outline: Get Successful
    Given the user is in the JSON place holder page
    When the user sends the <post> that he wants to get
    Then the user sees a status <code> and the post he wants with the id <post>
    Examples:
      | post | code |
      | 1    | 200  |
      | 10   | 200  |
      | 101  | 404  |
      | 100  | 200  |
      | 110  | 404  |