Feature: Nombre de pokemones
  Yo como usuario de pokeapi
  quiero saber la lista de pokemones disponibles en el sitio
  para poder saber cuales estan registrados

  Scenario Outline: Pokemon registrado
    Given el usuario esta en la PokeApi
    When el usuario hace la peticion con "<pokemon>"
    Then se valida que el <id> sea el del pokemon correspondiente


    Examples:
      | pokemon    | id |
      | bulbasaur  | 1  |
      | ivysaur    | 2  |
      | charmander | 4  |