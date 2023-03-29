Feature: Update Todos on the Page
  AS  JsonPlaceHolder user
  I WANT TO
  update an existing task on the site
  in order to change the task definition or status

  @Update
  Scenario Outline: Task Update Successful
    Given I have the service endpoint and the task id <id>
    When I send a PUT request with the task data that i want to update <userId>,<title>,<completed>
    Then I should see a successful status code and the response body updated.
    Examples:
      | id | userId | title          | completed |
      | 1  | "2"    | "Hacer oficio" | "true"    |
      | 2  | "10"   | ""             | "false"   |
      | 3  | ""     | "Cenar"        | ""        |