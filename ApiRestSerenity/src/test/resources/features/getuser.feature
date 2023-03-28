Feature: List Successful
  AS
  reqres admin
  I WANT TO
  list registered employees to the system
  SO THAT
  I can know the employees who are registered in the system

  @ListEmployee
  Scenario Outline: List employee Successful
    Given the admin is in the list page
    When send a list request with the <id>
    Then see a response with property "<lastName>" the "<avatar>" in addition to the <code>

    Examples:
      | id | lastName | avatar                                   | code |
      | 5  | Morris   | https://reqres.in/img/faces/5-image.jpg  | 200  |
      | 6  | Ramos    | https://reqres.in/img/faces/6-image.jpg  | 200  |
      | 7  | Lawson   | https://reqres.in/img/faces/7-image.jpg  | 200  |
      | 15 | Howell   | https://reqres.in/img/faces/12-image.jpg | 404  |