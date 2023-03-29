Feature: create post
  AS a json place holder user
  I WANT TO make a request to create the Post service
  SO THAT I CAN create a new post

  Scenario Outline: Create posts successfully
    Given url 'https://jsonplaceholder.typicode.com/posts'
    When request {"title":"<title>", "body": "<body>", "userId": <userId>}
    And method post
    Then status 201
    And match response == {"title": "<title>", "userId": <userId>,"body": "<body>", "id": #ignore}
    Examples:
      | title         | body                              | userId |
      | my first post | hello, this is my first post ever | 1      |
      | hello         | I just want to say hello!!!       | 3      |
      | karate        | I'm learning karate right now     | 2      |
      |               |                                   | 2000   |
      |               |                                   | null   |