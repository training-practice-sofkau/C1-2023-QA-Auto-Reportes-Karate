Feature: Obtener un usuario mediante una petición GET a la API de ReqRes

  Scenario: Obtener un usuario específico
    Given access to the Reqres URL
    When a method GET is performed
    Then status 200
    And I can see the users list
