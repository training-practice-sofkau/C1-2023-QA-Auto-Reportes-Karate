Feature: creando un post nuevo
  Yo como usuario de la pagina jsonplaceholder
  Quiero crear un post
  para luego verlo después

  Scenario Outline: creando un post
    Given estoy en la pagina de post de jsonplaceholder
    When creo un post con la informacion <userid>, <id>, <title>, <body>
    Then me debe devolver el <codigo> del resultado exitoso

    Examples:
      | userid | id | title                              | body     | codigo |
      | 1      | 1  | "Cien anuos de soledad"            | "autor1" | 201    |
      | 1      | 2  | "la importancia de morir a tiempo" | "autor2" | 201    |
      | 20     | A  | "lucifer"                          | "autor3" | 201    |

