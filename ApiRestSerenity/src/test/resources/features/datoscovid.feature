Feature: COVID Tracking API
  Como usuario interesado en obtener datos actualizados de COVID-19 en los Estados Unidos,
  Quiero poder obtener información sobre el número de casos positivos, negativos y pendientes,
  Para estar informado sobre la situación actual de la pandemia.

  Scenario Outline: Obtener datos de seguimiento de COVID-19 para casos positivos, negativos y pendientes
    Given que tengo el endpoint de la API de seguimiento de COVID-19
    When envio una solicitud GET al endpoint
    Then el codigo de estado de la respuesta debe ser <status_code> y la respuesta debe contener datos para <positive> casos, <negative> casos y <pending> casos

    Examples:
      | status_code | positive | negative | pending |
      | 200         | 28756489 | 74582825 | 11808   |
      | 200         | 28714654 | 74450990 | 11783   |
