Feature: Eliminar una tarea especifica de la API de jsonplaceholder

  Como usuario interesado en eliminar una de mis tareas,
  Quiero eliminar una de las tareas agregadas
  Para poder dejar solo las que me interesan


  Scenario Outline: Eliminar la tarea especifica
    Given url 'https://jsonplaceholder.typicode.com'
    And path '/todos/'+<id>
    When method delete
    Then status 200
    And match response == {}
    Examples:
      | id   |
      | "10" |
      | "20" |