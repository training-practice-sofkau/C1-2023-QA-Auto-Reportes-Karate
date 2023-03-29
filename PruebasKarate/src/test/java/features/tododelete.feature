Feature: Eliminar una tarea en la API
  Yo como una usuaria de la API
  Quiero poder eliminar una tarea en especifico
  Para tener solo la informacion  necesaria y veridica

  Background:
    * url 'https://dummyjson.com'

  Scenario Outline: Eliminación correcta de una tarea
    Given path '/todos/<id>'
    When method delete
    Then status <codestatus>
    And match response == { id: '#number', todo: '#string', completed: '#boolean', userId: '#number', isDeleted: true, deletedOn: '#string' }
    Examples:
      | id | codestatus |
      | 1  | 200        |
      | 5  | 200        |
      | 13 | 200        |

  Scenario Outline: Eliminacion incorrecta de una tarea
    Given path '/todos/<id>'
    When method delete
    Then status <codestatus>
    And match response == { message: "Todo with id '<id>' not found" }
    Examples:
      | id    | codestatus |
      | kmlñ6 | 404        |
      | ><    | 404        |
      | 1556  | 404        |
      | %     | 404        |


