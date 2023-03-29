
Feature: Servicio GET para datos de Covid-19 en el estado de New York
  Como usuario de la api del covid19
  Quiero ver el incremento de muertes para la fecha 20210307 en el estado de New York
  Para verificar que el servicio se encuentra funcionando correctamente

  Scenario: Obtener el numero de pruebas negativas de anticuerpos en el estado de New York
    Given el cliente esta configurado para conectarse a la API de CovidTracking
    When el cliente realiza una solicitud GET para New York para la fecha 20210307
    Then se debe recibir una respuesta con codigo de estado 200
    And se debe validar el incremento de muertes, totalTestsPeopleAntibody, positiveTestsPeopleAntibody y totalTestResults