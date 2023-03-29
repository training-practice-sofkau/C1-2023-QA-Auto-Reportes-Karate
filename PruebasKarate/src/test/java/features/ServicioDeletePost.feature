Feature: Prueba de servicio DELETE en JSONPlaceholder API

  Background:
    * url 'https://jsonplaceholder.typicode.com'

  Scenario Outline: Eliminar un post
    Given path 'posts', <postId>
    When method delete
    Then status 200

    Examples:
      | postId |
      | 1      |
      | 2      |