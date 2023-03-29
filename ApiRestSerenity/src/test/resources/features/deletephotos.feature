Feature: Eliminacion de fotos

  Yo como usuario
  Quiero eliminar una foto de la plataforma jsonHolder
  Para borrar información no deseada en la plataforma.

  Scenario Outline: Eliminación de una foto exitosa
    Given el usuario esta en la pagina de jsonplaceholder
    When el usuario realiza la peticion para eliminar una foto con el id interno <id>
    Then el usuario obtendra un <codigo> de estado exitoso

    Examples:
      | codigo | id   |
      | 200    | 1    |
      | 200    | 100  |
      | 200    | 5000 |

