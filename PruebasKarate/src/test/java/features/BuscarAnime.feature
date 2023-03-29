Feature: Obtener anime
  AS usuario de la web de anime
  WANT buscar nombres y caracteristicas de animes
  BEACAUSE ver mucho anime

  Scenario Outline: Obtener anime por id
    Given url 'https://api.jikan.moe/v4/anime/' + <id>
    When method GET
    Then status 200
    And match response.data != null
    And match response.data.mal_id == parseInt(id)
    And match response.data.url != null
    Examples:
      | id |
      | 1  |
      | 5  |
      | 16 |
      | 20 |

  Scenario: Obtener anime por id inexistente
    Given url 'https://api.jikan.moe/v4/anime/2'
    When method GET
    Then status 404
    And match response.message == "Resource does not exist"