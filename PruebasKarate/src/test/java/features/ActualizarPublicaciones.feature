Feature: Actualizar anime
  AS usuario de la web de anime
  WANT actualizar informacion de animes
  BEACAUSE quiero tener información actualizada

  Scenario Outline: Actualizar anime por id
    Given url 'https://jsonplaceholder.typicode.com/posts/' + <id>
    And request { "userId": 1, "title": <title>, "body": <body>}
    When method PUT
    Then status 200
    And match response.title == title
    And match response.id == parseInt(id)
    And match response.body == body
    Examples:
      | id | title     | body      |
      | 1  | Titulo 1  | cuerpo 1  |
      | 5  | Titulo 5  | cuerpo 5  |
      | 16 | Titulo 16 | cuerpo 16 |
      | 20 | Titulo 20 | cuerpo 20 |
