Feature: Agregar un nuevo comentario en la API
  Yo como una usuaria de la API
  Quiero poder agregar un nuevo comentario
  Para verificar el correcto funcionamiento del servicio y exponer mis ideas tambien

  Scenario Outline:Agregar exitosamente un nuevo comentario
    Given que tengo acceso a la API
    When hago una peticion de tipo POST para crear un nuevo comentario especificando <body> , <postId> y <userId>
    Then debe obtener un codigo de estado <codigo> y el JSON de creacion  correcto
    Examples:
      | body                                                                    | postId         | userId | codigo |
      | "Dont worry be happy!"                                                  | "4"            | "1"    | 200    |
      | "Life is too short to waste time on things that don't matter"           | "13"           | "2"    | 200    |
      | "I love spending time with my family"                                   | "20"           | "3"    | 200    |
      | "Happiness is not something ready made. It comes from your own actions" | "21"           | "4"    | 200    |
      | " "                                                                     | "15"           | "7"    | 200    |
      | "Sometimes the heart sees what is invisible to the eye"                 | "15"           | "0"    | 404    |
      | "Sometimes the heart sees what is invisible to the eye"                 | " "            | "0"    | 404    |


