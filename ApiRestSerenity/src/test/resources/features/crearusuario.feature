Feature: Crear usuario
  Como usuario de la api Reqres
  quiero crear un usuario
  Para poder usar los servicios del sistema

  @CrearUsuario
  Scenario Outline: Crear un nuevo producto
    Given que el usuario se encuentra en la página crear usuario
    When el usuario envia la peticion de crea un nuevo usuario con <nombre>, <job>
    Then el usuario debria ver un mensaje con informacion del nuevoo usuario creado con un estatus <code>
    Examples:
      | nombre     | job      | code |
      | "morpheus" | "leader" | 200  |
      | "morpheus" | "leader" | 200  |

