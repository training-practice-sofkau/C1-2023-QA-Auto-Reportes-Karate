Feature: Actualizar recursos mediante peticiones PUT en la API de JSONPlaceholder

  Scenario: Actualizar el título de una publicacion existente
    Given url 'https://jsonplaceholder.typicode.com/posts/1'
    And request { title: 'Prueba Karate framework', body: 'Esta es una prueba de Karate framework' }
    When method PUT
    Then status 200

  Scenario Outline: Eliminar publicaciones existente
    Given url 'https://jsonplaceholder.typicode.com/posts/<postId>'
    When method DELETE
    Then status 200

    Examples:
      | postId |
      | 1      |
      | 2      |
      | 3      |