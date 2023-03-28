Feature: Obtener simple usuario
  Como usuario de la api Reqres
  quiero conocer obtener un usuario
  Para poder usar los servicios del sistema


  @SimpleUsuario
  Scenario Outline: Obtener un usuario
    Given que el usuario se encuentra en la página obtener usuario
    When el usuario envía una solicitud con el <id> del usuario deseado
    Then el usuario  recibe un estatus <code> con usuario encontrado
    Examples:
      | id | code |
      | 1  | 200  |
      | 2  | 200  |
      | 3  | 200  |
      | 4  | 200  |