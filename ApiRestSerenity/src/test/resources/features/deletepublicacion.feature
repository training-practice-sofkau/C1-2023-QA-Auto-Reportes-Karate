Feature: Borrar una publicacion
  AS usuario de la pagina de JsonPlaceHolder
  WANT borrar un post
  BECAUSE no ocupar espacio con informacion antigua

  @BorrarPosts
  Scenario Outline: Borrar post por id
    Given El usuario se encuentra en la web de JsonPlaceHolder para borrar un post
    When El usuario envia una solicitud del post que se desea borrar con el <id>
    Then El usuario debe recibir un respuesta de status <code>
    Examples:
      | id     | code |
      | "5"    | 200  |
      | "6"    | 200  |
      | "10"   | 200  |
      | "abc"  | 200  |
      | "1512" | 200  |
