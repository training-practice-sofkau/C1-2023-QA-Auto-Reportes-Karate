#encoding:UTF-8
Feature: Eliminar albums
  AS  administrador de API de albums
  I WANT TO
  Eliminar albums por id
  SO THAT
  I depurar la lista de albums

  @delete
  Scenario Outline: eliminar albums
    Given el administrador esta en la pagina de albums
    When el usuario envia solicitud para eliminar albums  por id <id>
    Then la pagina retornara un estatus con codigo <codigo>
    Examples:
      | id  | codigo |
      | "1" | 200    |
      | "/" | 404    |
