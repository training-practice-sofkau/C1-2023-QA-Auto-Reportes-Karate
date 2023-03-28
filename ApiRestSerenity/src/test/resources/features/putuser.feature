Feature: Update employee Successful
  AS
  reqres admin
  I WANT TO
  make a update to a property to the system
  SO THAT
  I want to keep the employee database with up-to-date information

  @UpdateEmployee
  Scenario Outline: Update a property Successful
    Given the admin is in the update page
    When send a update request with the <id> the "<firstName>" and the "<email>"
    Then see a status <code> response code with update date and the "<firstName>"

    Examples:
      | id | firstName | email                  | code |
      | 1  | Geo       | george.bluth@reqres.in | 200  |
      | 2  | Jant      | janet.weaver@re.in     | 200  |
      | 3  | Emma      | emma@gmail.com         | 200  |
      | 4  | Evelin    | evelin.holt@reqres.in  | 200  |
