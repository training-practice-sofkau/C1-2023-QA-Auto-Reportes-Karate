Feature: Obtener una frase en especifico  de la API dummyJSON
  Yo como usuaria de dummyJSON API
  Quiero obtener una frase en especifico
  Para verificar el correcto funcionamiento del servicio

  Scenario Outline:Obtener correctamente una frase
    Given que tengo acceso a la API dummyJSON
    When hago una peticion de tipo GET para obtener una frase con un <id>  especificado
    Then se debe obtener un codigo de estado 200 y el JSON de respuesta correctamente
    Examples:
      | id   |
      | "2"  |
      | "5"  |
      | "11" |

