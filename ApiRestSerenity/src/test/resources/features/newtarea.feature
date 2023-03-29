Feature: Create Todos on the Page
  AS  JsonPlaceHolder user
  I WANT TO
  create a todo on the site
  in order to have a list of todos

  @NewTodo
  Scenario Outline: Succesfull Task Creation
    Given I have the service endpoint
    When the user send a POST request with the task data <userId>,<id>,<title>,<completed>
    Then the user sees a successful status code and the body of the response.
    Examples:
      | userId | id  | title          | completed |
      | "1"    | 201 | "Hacer oficio" | "false"   |