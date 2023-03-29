#encoding:UTF-8
Feature: Obtener ubicaciones
  AS  usuario de rickandmorty
  I WANT TO
  obtener ubicaciones por id
  SO THAT
  I ver datos de la ubicacion

  @Get
  Scenario Outline: buscar ubicaciones
    Given el usuario esta en la pagina
    When el usuario envia solicitud de busqueda  por id <id>
    Then la pagina retornara un estatus con codigo <codigo> nombre <name> tipo <type>

    Examples:
      | id     | codigo | name            | type     |  |  |
      | "1"    | 200    | "Earth (C-137)" | "Planet" |  |  |
      | "1000" | 404    | ""              | ""       |  |  |
      | ","    | 500    | ""              | ""       |  |  |



