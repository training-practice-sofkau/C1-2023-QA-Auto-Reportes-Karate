Feature: Realizar una publicacion
  AS usuario de la pagina de JsonPlaceHolder
  WANT publicar un nuevo post
  BECAUSE crear nuevas publicaciones

  @CrearPosts
  Scenario Outline: Actualizar post por id
    Given El usuario se encuentra en la web de JsonPlaceHolder para crear un post
    When El usuario envia una solicitud del post que se desea crear con <title> el <userid> y el <body>
    Then El usuario debe recibir un respuesta de status <code> y el post
    Examples:
      | title                           | body                                                             | userid | code |
      | "La historia del qa path happy" | "La apasionante historia de un qa que solo prueba cosas bonitas" | "5"    | 201  |
      | "CUMPLE, O NO CUMPLE"           | "Solo esa es la pregunta o blanco o negro"                       | "6"    | 201  |
      | "TESTIGOS DE QA"                | "Tiene un minuto de su vida para el ISTQB"                       | "5"    | 201  |
      | ""                              | ""                                                               | "999"  | 400  |