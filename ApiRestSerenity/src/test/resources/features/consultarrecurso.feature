Feature:consultar recurso por id
  yo como administrador del servicio REQRES
  quiero realizar una peticion de consulta de usuario por id en REQRES
  para ver los recursos registrados

  @ConsultarUsuario
  Scenario Outline:Consulta exitoso
    Given que el usuario esta en la pagina de consulta de recursos
    When el usuario envia una peticion get con el <id> del recurso
    Then debe retornar un codigo de respuesta <codigoEstado> y name <name>
    Examples:
      | id  | codigoEstado | name             |
      | "2" | 200          | "fuchsia rose"   |
      | "6" | 200          | "blue turquoise" |
