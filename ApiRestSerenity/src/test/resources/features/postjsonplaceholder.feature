Feature: creando un post nuevo
  Yo como usuario de la pagina jsonplaceholder
  Quiero crear un post
  para luego verlo despues

  Scenario Outline: creando un post
    Given estoy en la pagina de post de jsonplaceholder
    When creo un post con la informacion <userid>, <id>, <title>, <body>
    Then me debe devolver el <codigo> de respuesta y el <title> creado anteriormente

    Examples:
      | userid | id | title                              | body     | codigo |
      | 1      | 1  | "Cien anuos de soledad"            | "autor1" | 201    |
      | 1      | 2  | "La importancia de morir a tiempo" | "autor2" | 201    |
      | 20     | 3  | "Lucifer"                          | "autor3" | 201    |
