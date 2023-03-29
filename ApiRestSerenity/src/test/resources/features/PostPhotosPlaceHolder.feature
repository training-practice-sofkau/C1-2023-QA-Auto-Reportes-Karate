Feature: Crear registro de fotos en PlaceHolder
  Yo como usuario de la API de PlaceHolder
  Quiero poder crear un registro de fotos
  Para poder almacenar las fotos

  @RegistrarFoto
  Scenario Outline: Crear registro de fotos en PlaceHolder
    Given que el usuario ingresa a la API de PlaceHolder en la seccion de Photos
    When el usuario ingresa la informacion de <albumId>, <id>, <title>, <url> y  <thumbnailUrl>
    Then el usuario debe ver el registro de la foto con los datos ingreasados y un estatus <code>

    Examples:
      | albumId | id | title     | url        | thumbnailUrl | code |
      | 1       | 1  | "Titulo"  | " Prueba"  | "Prueba1URL" | 201  |
      | 2       | 2  | "Titulo2" | " Prueba2" | "Prueba2URL" | 201  |
      | 3       | 3  | "Titulo3" | " Prueba3" | "Prueba3URL" | 201  |

