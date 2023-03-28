Feature:consultar recurso por id
  yo como administrador del servicio REQRES
  quiero realizar una peticion de consulta de usuario por id en REQRES
  para ver los recursos registrados

  @ConsultarUsuario
  Scenario Outline:Consulta exitoso
    Given que el usuario esta en la pagina de consulta de recursos
    When el usuario envia una peticion get con el <id> del recurso
    Then el usuario debe observar un codigo de respuesta <codigoEstado> y la la informacion del recurso
    Examples:
      | id  | codigoEstado |
      | "5" | 200          |
      | "6" | 200          |
