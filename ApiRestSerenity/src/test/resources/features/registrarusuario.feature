Feature:Creacion de usuario
  yo como administrador del servicio REQRES
  quiero realizar una peticion de crear un usuario
  para registrar un nuevo usuario

  @RegisterUsuario
  Scenario Outline:Registro exitoso
    Given que el usuario esta en la pagina de registro de la api
    When el usuario envia una peticion post con el name <name> y job <job>
    Then el usuario debe ver un codeStatus <code> de respuesta y el id <id>
    Examples:
      | name       | job  | code | id   |
      | "Antonio"  | "QA" | 201  | 2 |
      | "Maria"    | "QA" | 201  | 24|
      | "Angelica" | "QA" | 201  | 22|