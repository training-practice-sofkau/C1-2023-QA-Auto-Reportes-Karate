Feature: Actualizando información de un usuario
  Yo como usuario de reqres
  Quiero actualizar la información de un usuario
  Para corregir informacion

  Scenario Outline: Put api test
    Given url 'https://reqres.in/api/users/'
    And request { id: <id>, first_name: <firstName>, Last_name: <lastName>, avatar: <avatar>}
    When method put
    Then status <code>
    And match response == { id: <id>, first_name: <firstName>, Last_name: <lastName>, avatar: <avatar>, updatedAt: "#notnull" }

    Examples:
      | id | firstName | lastName  | avatar          | code |
      | 1  | "James"   | "Munoz"   | "QA"            | 200  |
      | 2  | "Juan"    | "Pineda"  | "Coach"         | 200  |
      | 3  | "Ivan"    | "Marquez" | "Automatizador" | 200  |
