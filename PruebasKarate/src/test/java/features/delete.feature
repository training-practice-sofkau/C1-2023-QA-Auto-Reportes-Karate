Feature: Eliminando un usuario
  Yo como usuario del servicio reqres
  Quiero eliminar un usuario por id
  Para eliminarlo de los registros

  Scenario Outline: Delete api test
    Given url 'https://reqres.in/api/users/'+<id>
    When method delete
    Then status <codigo>


    Examples:
      | codigo | id |
      | 204    | 1  |
      | 204    | 2  |
      | 204    | 3  |


