Feature: Update
  AS Json Place Holder user
  I WANT to update a post
  SO THAT I can edit my posts

  @Update
  Scenario Outline: Update a post successfully
    Given url "https://jsonplaceholder.typicode.com/posts/"+<post>
    When request {userId:<userId>, title:<title>, body:<body>}
    And method put
    Then status <status>
    And match response == {id:"#notnull", title:<title>, body:<body>,userId:<userId>}

    Examples:
      | post | userId | title      | body            | status |
      | 1    | 1      | "Prueba 1" | "Prueba Karate" | 200    |
      | 50   | 2      | "Prueba 2" | "Hola Mundo"    | 200    |
      | 100  | 3      | "Prueba 3" | "Hola Juan"     | 200    |

  @Update
  Scenario Outline: Update a post not successfully
    Given url "https://jsonplaceholder.typicode.com/posts/"+<post>
    When request {userId:<userId>, title:<title>, body:<body>}
    And method put
    Then status <status>

    Examples:
      | post | userId | title      | body            | status |
      | 101  | 1      | "Prueba 1" | "Prueba Karate" | 500    |
      | 102  | 2      | "Prueba 2" | "Hola Mundo"    | 500    |
      | 103  | 3      | "Prueba 3" | "Hola Juan"     | 500    |