Feature: Prueba de servicio POST en JSONPlaceholder API

  Background:
    * url 'https://jsonplaceholder.typicode.com'

  Scenario Outline: Crear un nuevo post
    Given path 'posts'
    And request { title: '<title>', body: '<body>', userId: <userId> }
    When method post
    Then status 201
    And match response.title == '<title>'
    And match response.body == '<body>'
    And match response.userId == <userId>

    Examples:
      | title       | body                                                                                                                                                 | userId |
      | Terminator  | Segun la dilatacion de la pupila, la temperatura de la piel y las funciones motoras, calculo una probabilidad del 83 % de que no apriete el gatillo. | 1      |
      | John Connor | Todo lo que se es lo que me ensenno Terminator, nunca dejes de luchar.                                                                               | 2      |