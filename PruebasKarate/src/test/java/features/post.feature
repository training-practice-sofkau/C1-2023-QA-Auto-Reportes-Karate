Feature: Post successful
  AS Json Place Holder user
  I WANT to upload a post
  SO THAT I can interact with people

  @Post
  Scenario Outline: Post a post
    Given url "https://jsonplaceholder.typicode.com/posts"
    When request {userId:<userId>, title:<title>, body:<body>}
    And method post
    Then status <status>
    And match response == {id:"#notnull", title:<title>, body:<body>,userId:<userId>}

    Examples:
      | userId | title      | body            | status |
      | 1      | "Prueba 1" | "Prueba Karate" | 201    |
      | 2      | "Prueba 2" | "Hola Mundo"    | 201    |
      | 3      | "Prueba 3" | "Prueba falla"  | 201    |
