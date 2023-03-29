Feature: creando un post nuevo
  Yo como usuario de la pagina jsonplaceholder
  Quiero crear un post
  para luego verlo despues

  Scenario Outline: creando un post
    Given estoy en la pagina de post de jsonplaceholder
    When creo un post con la informacion <userid>, <title>, <body>
    Then me debe devolver el <codigo> de respuesta y el <title> creado anteriormente

    Examples:
      | userid | title                              | body     | codigo |
      | 1      | "Cien anuos de soledad"            | "autor1" | 201    |
      | 2      | "La importancia de morir a tiempo" | "autor2" | 201    |
      | 3      | "Lucifer"                          | "autor3" | 201    |
