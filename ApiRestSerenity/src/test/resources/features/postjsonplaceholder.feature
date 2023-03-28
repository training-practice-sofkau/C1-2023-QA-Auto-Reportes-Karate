Feature: creando un post nuevo
  Yo como usuario de la pagina jsonplaceholder
  Quiero crear un post
  para luego verlo después

  Scenario Outline: creando un post
    Given estoy en la pagina de post de jsonplaceholder
    When creo un post con la informacion <titulo>, <cuerpo>, <id>
    Then me debe devolver el post creado con <code> y <titulo> nuevo

    Examples:
      | titulo           | cuerpo    | id | code |
      | "cerveza aguila" | "malta"   | 1  | 200  |
      | "cerveza pilsen" | "cebada"  | 2  | 200  |
      | "cerveza club"   | "alcohol" | 3  | 200  |

