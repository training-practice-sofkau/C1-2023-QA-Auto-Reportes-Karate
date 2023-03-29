Feature: Actualizacion de un usuario en la api dummyjson
  Como usuario de la api dummyjson
  Quiero actualizar informacion de un usuario mediante su ID
  Para comprobar el correcto funcionamiento del servicio

  Background:
    Given que estoy en la pagina principal de dummyjson

  Scenario Outline: Actualizacion de un usuario de forma exitosa
    When envio una solicitud para actualizar al usuario con ID <userId>, con el nombre <firstName>, apellido <lastName>, correo electronico <email> y edad <age>
    Then deberia recibir una respuesta con el codigo de estado <statusCode> y su json de respuesta actualizado

    Examples:
      | userId | firstName | lastName    | email                       | age | statusCode |
      | "1"    | "Juan"    | "Perez"     | "juan.perez@email.com"      | 30  | 200        |
      | "2"    | "Maria"   | "Rodriguez" | "maria.rodriguez@email.com" | 45  | 200        |
      | "3"    | "Ana"     | "Morales"   | "ana.morales@email.com"     | 28  | 200        |
      | "4"    | "Pedro"   | "Fernandez" | "pedro.fernandez@email.com" | 55  | 200        |

  Scenario Outline: Actualización de un usuario de forma fallida
    When envia una solicitud para actualizar al usuario con ID incorrecto <Id>, con el nombre <firstname>, apellido <lastname>, correo electronico <correo> y edad <edad>
    Then deberia recibir una respuesta con el codigo de estado <code> y un mensaje de error

    Examples:
      | Id             | firstname | lastname    | correo                      | edad | code |
      | "9999"         | "Juan"    | "Perez"     | "juan.perez@email.com"      | 30   | 404  |
      | "-1"           | "Maria"   | "Rodriguez" | "maria.rodriguez@email.com" | 45   | 404  |
      | "12345"        | ""        | "Sanchez"   | "jose.sanchez@email.com"    | 35   | 404  |
      | "500000000000" | "Ana"     | "Morales"   | "ana.morales@email."        | 28   | 404  |