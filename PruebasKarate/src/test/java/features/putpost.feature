Feature: update post
  AS a json place holder user
  I WANT TO make a request to update a post
  SO THAT I CAN update a new post

  Scenario Outline: update a post
    Given url 'https://jsonplaceholder.typicode.com/posts/<requestId>'
    When request {"title":"<title>", "body": "<body>", "userId": <userId>, "id":<id>}
    And method put
    Then status 200
    And match response == {"title": "<title>", "userId": <userId>,"body": "<body>", "id":<requestId>}
    Examples:
      | title          | body                        | userId | id | requestId |
      | editing a post | hello, this an updated post | 1      | 1  | 1         |
      | updated post   | updating this post          | 3      | 2  | 2         |
      | still karate   | I'm still learning karate   | 2      | 30 | 3         |

  Scenario Outline: invalid id
    Given url 'https://jsonplaceholder.typicode.com/posts/<id>'
    When request {"title":"<title>", "body": "<body>", "userId": <userId>}
    And method put
    Then status <code>
    Examples:
      | title          | body                      | userId | id  | code |
      | editing a post |                           | 500    | 200 | 500  |
      |                | updating this post        | 400    | 250 | 500  |
      | still karate   | I'm still learning karate | 1000   |     | 404  |
