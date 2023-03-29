Feature: Nombre de pokemones
  Yo como usuario de pokeapi
  quiero saber la lista de pokemones disponibles en el sitio
  para poder saber cuales estan registrados

  Scenario Outline: Pokemon registrado
    Given el usuario esta en la PokeApi
    When el usuario hace la peticion con "<pokemon>"
    Then se valida que el <id> y el <status_code> sean correctos


    Examples:
      | pokemon   | id | status_code |
      | bulbasaur | 1  | 200         |
      | ivysaur   | 2  | 200         |
      | solsagan  | 3  | 404         |