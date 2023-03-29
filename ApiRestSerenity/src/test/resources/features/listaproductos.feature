Feature: Obtener un limite de productos en la API
  Yo como usuaria de Fake Store API
  Quiero obtener una lista de productos poniendo un limite
  Para verificar el correcto funcionamiento del servicio

  Scenario Outline:Obtener correctamente una lista de productos poniendo un limite
    Given que tengo acceso a la API Fake Store
    When hago una peticion de tipo GET para obtener la lista con un <limite>  de productos
    Then debo obtener un codigo de estado 200 y el JSON de respuesta correctamente
    Examples:
      | limite        |
      | "2"           |
      | "5"           |
      | "3"           |

