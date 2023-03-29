Feature: : Crear usuario
  Yo como usuario de API reqresIN
  quiero hacer una peticion POST
  para crear un usuario

  @Crear
  Scenario Outline: Peticion exitosa
    Given el usuario esta esta en la API
    When hace una peticio POST con <name> y <job>
    Then puede ver la informacion de usuario y <status>
    Examples:
      | name       | job      | status |
      | "morpheus" | "leader" | 200    |
      | ""         | "police" | 400    |
      | "1123"     | "232112" | 400    |