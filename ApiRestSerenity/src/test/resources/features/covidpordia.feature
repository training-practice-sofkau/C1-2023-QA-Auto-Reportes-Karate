Feature: : Ver informacion de muertes por covid
  Yo como usuario de API CovidTracking
  quiero hacer una peticion GET
  para visualizar la informacion de muertes por covid por fecha

  @Informacion
  Scenario Outline: Peticion exitosa
    Given el usuario esta en la API
    When se hace peticion get con <fecha>
    Then recibe <status> y la informacion de la fecha
    Examples:
      | fecha       | status |
      | "2021-01-02"|  200    |
      | "2022-02-02"|  200    |
      | "2020-02-01"|  200    |