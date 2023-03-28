Feature: Crear usuario
  Como usuario de la api Reqres
  quiero crear un usuario
  Para poder usar los servicios del sistema

  @CrearUsuario
  Scenario Outline: Crear un nuevo producto
    Given que el usuario se encuentra en la página crear usuario
    When el usuario envia la peticion de crea un nuevo usuario con <name>, <job>
    Then el usuario debria ver un mensaje con informacion del nuevoo usuario creado con un estatus <code>
    Examples:
      | name       | job      | code |
      | "morpheus" | "leader" | 201  |
      | "morpheus" | "leader" | 201  |

