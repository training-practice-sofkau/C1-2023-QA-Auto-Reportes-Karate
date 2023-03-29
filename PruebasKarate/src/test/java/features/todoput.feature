Feature: Actualizar una tarea en la API
  Yo como una usuaria de la API
  Quiero poder actualizar una tarea
  Para verificar el correcto funcionamiento del servicio y tener la información al día

  Scenario Outline: Actualizar exitosamente una tarea
    Given url 'https://dummyjson.com'
    And path '/todos/<id>'
    When request { "todo": "<todo>", "completed": <completed>, "userId": <userId> }
    And method put
    Then status: <codestatus>
    And match response == { "id": <id>, "todo": "<todo>", "completed": <completed>, "userId": <userId> }

    Examples:
      | id | todo                                 | completed | userId | codestatus |
      | 1  | crear correctamente la documentacion | false     | 1      | 200        |
      | 5  | implementar el framework de Karate   | true      | 7      | 200        |
      | 6  | hacer los resportes muy bien         | false     | 12     | 200        |
      | 7  | organizar la habitacion              | true      | 15     | 200        |

